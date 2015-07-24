package cn.creditloans.tools.lucene;

/**
 * Copyright Manning Publications Co.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific lan      
 */

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;

/**
 * Drop-in replacement for IndexWriter that uses multiple threads, under the
 * hood, to index added documents.
 */

public class ThreadedIndexWriter extends IndexWriter {

	/**
	 * 在Java5之后，任务分两类：一类是实现了Runnable接口的类，一类是实现了Callable接口的类。
	 * 两者都可以被ExecutorService执行，但是 Runnable任务没有返回值，而Callable任务有返回值。
	 * 并且Callable的call（）方法只能通过ExecutorService的 submit（Callable<T> task） 方法来
	 * 执行，并且返回一个 <T> Future<T>，是表示任务等待完成的 Future.
	 */
	private ExecutorService threadPool;

	private class Job implements Runnable { // A
		Iterable<? extends IndexableField> doc;
		Analyzer analyzer;
		Term delTerm;

		public Job(Iterable<? extends IndexableField> doc, Term delTerm,
				Analyzer analyzer) {
			this.doc = doc;
			this.analyzer = analyzer;
			this.delTerm = delTerm;
		}

		public void run() { // B
			try {
				/**
				 * 调用updateDocument的方法，传给它一个新的doc来更新数据， Term term = new Term("id","1234567");
				 * 先去索引文件里查找id为1234567的Doc,如果有就更新它(如果有多条，最后更新后只有一条)。如果没有就新增.
				 * 数据库更新的时候，我们可以只针对某个列来更新，而lucene只能针对一行数据更新。 
				 */
				ThreadedIndexWriter.super.updateDocument(delTerm, doc, analyzer);
			} catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}
	}

	public ThreadedIndexWriter(Directory dir, IndexWriterConfig config,
			int numThreads, int maxQueueSize) throws CorruptIndexException,
			IOException {
		/**
		 * Directory directory; 指向索引文件夹
		 * 
		 */
		super(dir, config);
		// Lucene 4 采用 DocumentsWriterPerThread的方式，用fair=true构建ArrayBlockingQueue，
		// 保证线程分配公平，得到差不多大小的segment。
		// setRAMBufferSizeMB采用较大的值也有利于平均分配
		// Lucene 3 采用 MergeWriter的方式，所以得到的 segment 大小是一样的。
		/**
		 * 线程池
		 * ThreadPoolExecutor(int corePoolSize, int maximumPoolSize,long keepAliveTime, TimeUnit unit,
		 * 		BlockingQueue<Runnable> workQueue,RejectedExecutionHandler handler)
		 * 	corePoolSize：线程池维护线程的最少数量 （core : 核心）
		 * 	maximumPoolSize：线程池维护线程的最大数量
		 * 	keepAliveTime：线程池维护线程所允许的空闲时间
		 * 	unit：线程池维护线程所允许的空闲时间的单位
		 * 	workQueue：线程池所使用的缓冲队列
		 * 	handler：线程池对拒绝任务的处理策略
		 * 一个任务通过 execute(Runnable)方法被添加到线程池，任务就是一个 Runnable类型的对象，任务的执行方法就是 Runnable类型对象的run()方法。
		 * 处理任务的优先级为：corePoolSize、任务队列workQueue、最大线程maximumPoolSize，如果三者都满了，使用handler处理被拒绝的任务。 
		 * ArrayBlockingQueue：一个由数组支持的有界阻塞队列。此队列按 FIFO（先进先出）原则对元素进行排序。队列的头部 是在队列中存在时间最
		 * 		长的元素。队列的尾部 是在队列中存在时间最短的元素。新元素插入到队列的尾部，队列获取操作则是从队列头部开始获得元素。
		 * ThreadPoolExecutor.CallerRunsPolicy：重试添加当前的任务，他会自动重复调用execute方法。
		 */
		threadPool = new ThreadPoolExecutor(
				numThreads, numThreads, 0, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(maxQueueSize, false),
				new ThreadPoolExecutor.CallerRunsPolicy());
	}

	public void updateDocument(Term term,
			Iterable<? extends IndexableField> doc, Analyzer a) { // D
		threadPool.execute(new Job(doc, term, a)); // D
	}

	public void close() throws CorruptIndexException, IOException {
		finish();
		super.close();
	}

	@SuppressWarnings("deprecation")
	public void close(boolean doWait) throws CorruptIndexException, IOException {
		finish();
		super.commit();
		super.close(doWait);
	}

	public void rollback() throws CorruptIndexException, IOException {
		finish();
		super.rollback();
	}

	private void finish() { // E
		threadPool.shutdown();
		while (true) {
			try {
				if (threadPool.awaitTermination(Long.MAX_VALUE,
						TimeUnit.SECONDS)) {
					break;
				}
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
				throw new RuntimeException(ie);
			}
		}
	}
}