package com.intellicredit.platform.util;

import java.io.*;
import java.util.jar.*;
import java.util.zip.ZipEntry;

public class RootDirClassLoader extends ClassLoader {
	private String m_rootDir;

	public RootDirClassLoader() {
	}

	public RootDirClassLoader(String rootPath) {
		m_rootDir = rootPath;
	}

	public void setRootDir(String rootDir) {
		m_rootDir = rootDir;
	}

	public Class<?> findClass(String name) throws ClassNotFoundException {
		if (m_rootDir == null)
			throw new ClassNotFoundException(name);

		Class<?> clas = null;
		byte[] raw = null;
		try {
			String fileStub = name.replace('.', File.separatorChar) + ".class";
			raw = getBytes(m_rootDir + File.separatorChar + fileStub);

			if (raw == null) {
				fileStub = name.replace('.', '/') + ".class";
				File[] files = FileUtil.getFiles(new File(m_rootDir),
						new String[] { ".jar" });
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory())
						continue;
					raw = getBytes(files[i].getPath(), fileStub);
					if (raw != null)
						break;
				}
			}
			if (raw != null)
				clas = defineClass(name, raw, 0, raw.length);
			else
				throw new ClassNotFoundException(name);
		} catch (IOException ie) {
			throw new ClassNotFoundException(name, ie);
		}
		return clas;
	}

	private byte[] getBytes(String filename) throws IOException {
		File file = new File(filename);
		if (!file.exists())
			return null;
		int len = (int) file.length();
		byte raw[] = new byte[len];
		FileInputStream fin = new FileInputStream(file);
		int r = fin.read(raw);
		fin.close();
		if (r != len)
			throw new IOException("Can't read all, " + r + " != " + len);
		return raw;
	}

	private byte[] getBytes(String filename, String entryname)
			throws IOException {
		File file = new File(filename);
		if (!file.exists())
			return null;
		JarFile jarfile = new JarFile(filename);
		ZipEntry entry = jarfile.getEntry(entryname);
		if (entry == null)
			return null;
		int len = (int) entry.getSize();
		byte raw[] = new byte[len];
		InputStream in = jarfile.getInputStream(entry);
		int r = 0;
		for (int a = 0; a < len; a += r)
			r = in.read(raw, a, len - a);

		in.close();
		jarfile.close();
		return raw;
	}
}
