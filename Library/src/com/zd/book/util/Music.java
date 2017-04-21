package com.zd.book.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class Music {
	static File file1 = new File("src/music/Dance Flow - 迷人的危险 紫色迷情.wav");
	static AudioClip sound1;
	public static int flags;
	public static void begin() throws MalformedURLException{
		sound1 = Applet.newAudioClip(file1.toURL());
		sound1.play();
		flags=1;
	}
	public static void over(){
		sound1.stop();
		flags=0;
	}
}
