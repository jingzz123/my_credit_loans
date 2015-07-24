package cn.creditloans.tools.fuzzy.util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;

import cn.creditloans.tools.lucene.ThreadedIndexWriter;


public class FuzzyUtil {
	
    public static Directory getRamDirectory() {
    	/**
    	 * RAMDirectory是将即将索引的数据资源存在在内存中而不是磁盘中，这使得文件的读写操作非常的迅速缺点：
    	 * 因为在内存中，所以在程序退出后索引数据就不存在了。
    	 */
    	/**
    	 * RAMDirectory：常驻内存的Directory实现方式。默认通过SingleInstanceLockFactory（单实例锁工厂）进行锁
    	 * 的实现。该类不适合大量索引的情况。另外也不适用于多线程的情况。  在索引数据量大的情况下建议使用MMapDirectory
    	 * 代替。RAMDirectory是Directory抽象类在使用内存最为文件存储的实现类，其主要是将所有的索引文件保存到内存中。
    	 * 这样可以提高效率。但是如果索引文件过大的话，则会导致内存不足，因此，小型的系统推荐使用，如果大型的，索引文
    	 * 件达到G级别上，推荐使用FSDirectory
    	 */
    	return new RAMDirectory();
    }
    
    /**
     * Directory的实现类可以分为文件目录，内存目录和目录的代理类及工具类。
     * @return
     * @throws IOException
     */
    public static Directory getSimpleFSDirectory(String indexPath) throws IOException {
    	/**
    	 * 其中常用的就是FSDirectory：表示对文件系统目录的操作。RAMDirectory ：内存中的目录操作。
    	 * SimpleFSDirectory:FSDirectory的简单实现,并发能力有限，遇到多线程读同一个文件时会遇到瓶颈，
    	 * 通常用NIOFSDirectory或MMapDirectory代替
    	 */
    	return new SimpleFSDirectory(new File(indexPath));
    }
    
    public static Directory getNIOFSDirectory(String indexPath) throws IOException {
    	/**
    	 * NIOFSDirectory：通过java.nio's FileChannel实行定位读取，支持多线程读（默认情况下是线程安全的）。该类仅使用FileChannel
    	 * 进行读操作，写操作则是通过FSIndexOutput实现。 注意：NIOFSDirectory 不适用于Windows系统，另外如果一个访问该类的线程，在IO
    	 * 阻塞时被interrupt或cancel，将会导致底层的文件描述符被关闭，后续的线程再次访问NIOFSDirectory时将会出现ClosedChannelException
    	 * 异常，此种情况应用SimpleFSDirectory代替。
    	 */
        return new NIOFSDirectory(new File(indexPath));
    }
    
    public static Directory getMMapDirectory(String indexPath) throws IOException {
        /**
         * MMapDirectory：通过内存映射进行读，通过FSIndexOutput进行写的FSDirectory实现类。使用该类时要保证用足够的虚拟地址空间。
         * 另外当通过IndexInput的close方法进行关闭时并不会立即关闭底层的文件句柄，只有GC进行资源回收时才会关闭。
         */
    	return new MMapDirectory(new File(indexPath));
    }
    
    /**
     * 得到地址的分析器
     * @return
     */
    public static Analyzer getAddressAnalyzer() {
       	//      分析器类型            基本介绍 
    	//    WhitespaceAnalyzer   以空格作为切词标准，不对语汇单元进行其他规范化处理 
    	//    SimpleAnalyzer       以非字母符来分割文本信息，并将语汇单元统一为小写形式，并去掉数字类型的字符 
    	//    StopAnalyzer         该分析器会去除一些常有a,the,an等等，也可以自定义禁用词 
    	//    StandardAnalyzer     Lucene内置的标准分析器，会将语汇单元转成小写形式，并去除停用词及标点符号 
    	//    CJKAnalyzer          能对中，日，韩语言进行分析的分词器，对中文支持效果一般。 
    	//    SmartChineseAnalyzer 对中文支持稍好，但扩展性差 ，smartcn是lucene自带的一个中文分词工具，它源自中科院的ICTCLAS中文分词系统。
        CharArraySet defaultStopSet = SmartChineseAnalyzer.getDefaultStopSet();//得到系统默认的停用词
        Set<String> stopSet = new HashSet<String>();
        Iterator<Object> iterator = defaultStopSet.iterator();
        while (iterator.hasNext()) {
            stopSet.add(new String((char[])iterator.next()));
        }
        // 中英文混合分词器(其他几个分词器对中文的分析都不行)
        return new SmartChineseAnalyzer(CharArraySet.copy(stopSet));
    }
    
    /**
     * 得到公司名称的分析器
     * @return
     */
    public static Analyzer getCorpAnalyzer() {
       	//      分析器类型            基本介绍 
    	//    WhitespaceAnalyzer   以空格作为切词标准，不对语汇单元进行其他规范化处理 
    	//    SimpleAnalyzer       以非字母符来分割文本信息，并将语汇单元统一为小写形式，并去掉数字类型的字符 
    	//    StopAnalyzer         该分析器会去除一些常有a,the,an等等，也可以自定义禁用词 
    	//    StandardAnalyzer     Lucene内置的标准分析器，会将语汇单元转成小写形式，并去除停用词及标点符号 
    	//    CJKAnalyzer          能对中，日，韩语言进行分析的分词器，对中文支持效果一般。 
    	//    SmartChineseAnalyzer 对中文支持稍好，但扩展性差 ，smartcn是lucene自带的一个中文分词工具，它源自中科院的ICTCLAS中文分词系统。
        CharArraySet defaultStopSet = SmartChineseAnalyzer.getDefaultStopSet();//得到系统默认的停用词
        Set<String> stopSet = new HashSet<String>();
        Iterator<Object> iterator = defaultStopSet.iterator();
        while (iterator.hasNext()) {
            stopSet.add(new String((char[])iterator.next()));
        }
        stopSet.add("公司");
        stopSet.add("有限公司");
        // 中英文混合分词器(其他几个分词器对中文的分析都不行)
        return new SmartChineseAnalyzer(CharArraySet.copy(stopSet));
    }
    
    /**
     * 
     * @param indexPath：索引文件存放的目录
     * @param threadNum：同时启动的建立索引的线程的数量
     * @return
     * @throws IOException
     */
    public static IndexWriter getWriter(String indexPath, int threadNum, int type) throws IOException {
        
        // 创建内存索引
        //Directory dir = getRamDirectory();
        //Directory dir = getSimpleFSDirectory();
        Directory dir = getMMapDirectory(indexPath);
        
        /**
         * 创建IndexWriter实例时，通过IndexWriterConfig来设置其相关配置
         * matchVersion：所用Lucene的版本
         * Analyzer：分析器
         * 
         */
        Analyzer analyzer = null;
        if(type==FuzzyConstants.FUZZY_TYPE_ADDRESS){
        	analyzer = getAddressAnalyzer();
        }else{
        	analyzer = getCorpAnalyzer();
        }
        IndexWriterConfig conf = new IndexWriterConfig(FuzzyConstants.LUCENE_VER, analyzer);
        
        //LogMergePolicy mergePolicy = new LogByteSizeMergePolicy();
        //mergePolicy.setMergeFactor(1000);
        //mergePolicy.setMaxMergeDocs(Integer.MAX_VALUE);
        //conf.setMergePolicy(mergePolicy);
        //conf.setMaxThreadStates(9);
        //((ConcurrentMergeScheduler)conf.getMergeScheduler()).setMaxMergesAndThreads(9, 9);
        
        /**
         * ramBufferSizeMB：随机内存 默认为16M.用于控制buffer索引文档的内存上限，如果buffer的索引文档个数到达该上限就写入硬盘。
         * 当然，一般来说越大索引速度越快
         */
        //conf.setRAMBufferSizeMB(200).setMaxBufferedDocs(30000);
        //conf.setOpenMode(OpenMode.CREATE);
        conf.setRAMBufferSizeMB(64);
        
        // 多线程建立索引
        return new ThreadedIndexWriter(dir, conf, threadNum, 4 * threadNum);
        //return new IndexWriter(dir, conf);
    }
    
    /**
     * 创建多线程执行服务
     * @param threadNum
     * @return
     */
    public static ExecutorService createService(int threadNum){
    	//开启多个线程同时进行
    	return new ThreadPoolExecutor( // C
        		threadNum, threadNum, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(threadNum * 4, false), 
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
    
    /**
     * 关闭多线程执行服务
     * @param threadPool
     */
    public static void shutdownService(ExecutorService threadPool){
        threadPool.shutdown();
        while (true) {
            try {
                if (threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)) {
                    break;
                }
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(ie);
            }
        }
    }
    
    public static String getUUID(){
    	UUID id = UUID.randomUUID();
    	String uuid = id.toString().replaceAll("-", "");
    	return uuid;
    }

 }
