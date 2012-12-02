package com.zenred.eadvert.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class CampaignService3Test {
	
	@Test
	public void testUriSplitter(){
		String generatedFile = "<p><img width=\"100\" height=\"100\" align=\"left\" alt=\"\" style=\"margin-right: 10px;\" src=\"http://www.email_advert.com/_m/SpeakerGreen_256.png\" /></p><h3>HELLO!</h3><p>happy happy joy joy&nbsp;&nbsp;&nbsp; <img src=\"http://www.email_advert.com/fckeditor/editor/images/smiley/msn/shades_smile.gif\" alt=\"\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src=\"http://www.email_advert.com/fckeditor/editor/images/smiley/msn/lightbulb.gif\" alt=\"\" /></p><p>&nbsp;</p>";
		List<String> uriList = CampaignService.imageURIs(generatedFile);
		for(String uri : uriList){
			System.out.println(uri);
		}
		assertTrue(uriList.size()>0);
	}

}
