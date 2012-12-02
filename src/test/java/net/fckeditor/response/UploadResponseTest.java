/*
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2004-2010 Frederico Caldeira Knabben
 * 
 * == BEGIN LICENSE ==
 * 
 * Licensed under the terms of any of the following licenses at your
 * choice:
 * 
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 * 
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 * 
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 * 
 * == END LICENSE ==
 */
package net.fckeditor.response;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for {@link UploadResponse}.
 * 
 * @version: $Id: UploadResponseTest.java 4785 2009-12-21 20:10:28Z mosipov $
 */
public class UploadResponseTest {

	@Test(expected = IllegalArgumentException.class)
	public void noArguments() throws Exception {
		new UploadResponse();
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooManyArguments() throws Exception {
		new UploadResponse(101, "/some/url/file.txt", "file.txt",
				"something's wrong", "arg no. 5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void notANumber() throws Exception {
		new UploadResponse("1");
	}

	@Test
	public void onlyErrorNumber() throws Exception {
		UploadResponse actual = new UploadResponse(
				UploadResponse.EN_INVALID_FILE_TYPE_ERROR);
		String expected = "<script type=\"text/javascript\">\n"
				+ "(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;try{document.domain=d;}catch (e){break;}}})();\n"
				+ "window.parent.OnUploadCompleted(202);\n</script>";
		assertEquals(expected, actual.toString());
	}
	
	@Test
	public void fourArguments() throws Exception {
		UploadResponse actual = new UploadResponse(UploadResponse.EN_OK,"/fckeditor-java/userfiles/image/fredck.jpg");
		String expected = "<script type=\"text/javascript\">\n"
				+ "(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;try{document.domain=d;}catch (e){break;}}})();\n"
				+ "window.parent.OnUploadCompleted(0,'/fckeditor-java/userfiles/image/fredck.jpg');\n</script>";
		assertEquals(expected, actual.toString());
	}
	
	@Test
	public void renamedFile() throws Exception {
		UploadResponse actual = new UploadResponse(UploadResponse.EN_FILE_RENAMED_WARNING,"/fckeditor-java/userfiles/image/hacked_php.txt","hacked_php.txt");
		String expected = "<script type=\"text/javascript\">\n"
				+ "(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;try{document.domain=d;}catch (e){break;}}})();\n"
				+ "window.parent.OnUploadCompleted(201,'/fckeditor-java/userfiles/image/hacked_php.txt','hacked_php.txt');\n</script>";
		assertEquals(expected, actual.toString());
	}

	@Test
	public void customMessage() throws Exception {
		UploadResponse actual = new UploadResponse(UploadResponse.EN_CUSTOM_ERROR);
		actual.setCustomMessage("some error");
		String expected = "<script type=\"text/javascript\">\n"
				+ "(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;try{document.domain=d;}catch (e){break;}}})();\n"
				+ "window.parent.OnUploadCompleted(1,'','','some error');\n</script>";
		assertEquals(expected, actual.toString());
	}


	@Test
	public void nullArguments() throws Exception {
		UploadResponse actual = new UploadResponse(UploadResponse.EN_CUSTOM_ERROR,null,null,null);
		String expected = "<script type=\"text/javascript\">\n"
				+ "(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;try{document.domain=d;}catch (e){break;}}})();\n"
				+ "window.parent.OnUploadCompleted(1,'','','');\n</script>";
		assertEquals(expected, actual.toString());
	}

}
