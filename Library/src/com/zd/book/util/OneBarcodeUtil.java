package com.zd.book.util;

import java.awt.image.BufferedImage;  
import java.io.FileOutputStream;  
import org.jbarcode.JBarcode;  
import org.jbarcode.encode.Code39Encoder;  
import org.jbarcode.encode.EAN13Encoder;  
import org.jbarcode.paint.BaseLineTextPainter;  
import org.jbarcode.paint.EAN13TextPainter;  
import org.jbarcode.paint.WideRatioCodedPainter;  
import org.jbarcode.paint.WidthCodedPainter;  
import org.jbarcode.util.ImageUtil;  

/** 
 * 条形码的生成
 */  
public class OneBarcodeUtil {  


	public String OneBarcode(String str,String filename){
		try  
		{  
			JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN13TextPainter.getInstance());  
			//生成. 欧洲商品条码(=European Article Number)  
			//这里我们用作图书条码  
			localJBarcode.setEncoder(Code39Encoder.getInstance());  
			localJBarcode.setPainter(WideRatioCodedPainter.getInstance());  
			localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());  
			localJBarcode.setShowCheckDigit(false);  
			BufferedImage localBufferedImage = localJBarcode.createBarcode(str);  
			saveToGIF(localBufferedImage, ""+filename+".gif");  
		}  
		catch (Exception localException)  
		{  
			localException.printStackTrace();  
		}  
		return "ok";
	}
	//---------------------------------------------------------

	static void saveToJPEG(BufferedImage paramBufferedImage, String paramString)  
	{  
		saveToFile(paramBufferedImage, paramString, "jpeg");  
	}  

	static void saveToPNG(BufferedImage paramBufferedImage, String paramString)  
	{  
		saveToFile(paramBufferedImage, paramString, "png");  
	}  

	static void saveToGIF(BufferedImage paramBufferedImage, String paramString)  
	{  
		saveToFile(paramBufferedImage, paramString, "gif");  
	}  

	static void saveToFile(BufferedImage paramBufferedImage, String paramString1, String paramString2)  
	{  
		try  
		{  
			FileOutputStream localFileOutputStream = new FileOutputStream("d:/images/" + paramString1);  
			ImageUtil.encodeAndWrite(paramBufferedImage, paramString2, localFileOutputStream, 96, 96);  
			localFileOutputStream.close();  
		}  
		catch (Exception localException)  
		{  
			localException.printStackTrace();  
		}  
	}  

} 