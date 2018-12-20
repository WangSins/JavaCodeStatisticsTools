package priv.wsins.statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

public class FileTraverseJava {

	public static void main(String[] args) throws IOException {
		// 获取目录下java文件

		File dir = new File("D:\\Workspace\\EclipseProject");
		FileFilter filter = new FileFilterBySuffix(".java");
		getFileList(dir, filter);
	}

	private static void getFileList(File dir, FileFilter filter) throws IOException {
		File[] files = dir.listFiles();
		int count = 0;
		for (File file : files) {
			if (file.isDirectory()) {
				getFileList(file, filter);
			} else {
				// 如果是文件，传递到过滤器中去过滤
				if (filter.accept(file)) {
					System.out.print(file + " Lines:");
					count += readText(file.getPath());
				}
			}
		}
		if(count!=0){
			System.out.println("Total file lines:"+count);
			System.out.println("Directory path="+dir.getPath());
		}
		
	}

	public static int readText(String fpath) throws IOException {

		BufferedReader bufr = new BufferedReader(new FileReader(fpath));
		String line = null;
		int count = 1;
		while ((line = bufr.readLine()) != null) {
			count++;
		}
		System.out.println(count);

		return count;
	}

}
