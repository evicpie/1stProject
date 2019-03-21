package com.tj.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.dao.LibraryDao;

public class LInsertService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("libraryUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String lImage = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, 
									"utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			lImage = mRequest.getFilesystemName(param);
			// lTitle, lWriter, lYear, lPublicsher, lLocation, lImage, lKeyword, lBorrow, lMany, bNumber, lDel
			String lTitle = mRequest.getParameter("lTitle");
			String lWriter = mRequest.getParameter("lWriter");
			int lYear = Integer.parseInt(mRequest.getParameter("lYear"));
			String lPublicsher = mRequest.getParameter("lPublicsher");
			String lLocation = mRequest.getParameter("lLocation");
			String lKeyword = mRequest.getParameter("lKeyword");
			int bNumber = Integer.parseInt(mRequest.getParameter("bNumber"));
			LibraryDao lDao = new LibraryDao();
			int result = lDao.insertBook(lTitle, lWriter, lYear, lPublicsher, lLocation, lImage, lKeyword, bNumber);
			if(result == LibraryDao.SUCCESS) { 
				request.setAttribute("resultMsg", "글쓰기 성공");
			}else {
				request.setAttribute("resultMsg", "글쓰기 실패");
			}
			// 결과에 따라 적절히 request.setAttribute
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		File serverFile = new File(path+"/"+lImage);
		if(serverFile.exists()) {
			InputStream  is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/mega_IT/source/6_JSP/1stProject/WebContent/libraryUp/"+lImage);
				byte[] bs = new byte[(int)serverFile.length()];
				while(true) {
					int nByteCnt = is.read(bs);
					if(nByteCnt==-1) break;
					os.write(bs, 0, nByteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

}
