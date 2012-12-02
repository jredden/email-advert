package com.zenred.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestSillyUtil {
	
	private static int count = 64;
	
	@Test
	public void testBytes(){
		byte [] stuff = new byte[1];
		stuff[0]=0;
		for(int idex = 1; idex < count; idex++){
			byte [] newstuff = new byte[stuff.length+1];
			newstuff[idex]=(byte) idex;
			stuff = newstuff;
		}
		System.out.println(stuff);
		System.out.println(stuff.length);

		assertTrue(stuff.length == 64);
	}
	
	@Test
	public void testBytes2(){
		byte [] readBuffer = new byte[]{1,2,3,4,10,11,12,13,14,20,21,22,23,24,0,0,0,0,0};
		int count=0;
		byte [] stuff = new byte[1];
		for(int idex = 0; true; idex++){
			byte [] newstuff = new byte[stuff.length+1];
			newstuff[idex]=readBuffer[idex];
			System.out.print(newstuff[idex]);
			stuff = newstuff;
			if(newstuff[idex]==0){
				++count;
			}
			else{
				count = 0;
			}
			if(count >= 4){
				// the end
				break;
			}
		}
		System.out.println(stuff);
		System.out.println(stuff.length);
		assertTrue(stuff.length > 0);
	}
}
