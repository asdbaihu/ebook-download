package com.cweijan.service;

import com.cweijan.util.HttpUtils;
import com.cweijan.util.Log;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;

public class EbookService{

    private String diretory;

    public EbookService(String diretory){

        this.diretory = diretory;

        File file = new File(this.diretory);
        if(!file.exists()){
            file.mkdirs();
        }

    }

    public void downloadPdf(String bookCode, Integer count) throws IOException{

        if(bookCode == null || "".equals(bookCode)){
            return;
        }
        long startTime = System.currentTimeMillis();


        Log.getLogger().info("start downlaod pdf for bookcode:" + bookCode + ", page count is " + count);

        PDDocument document = new PDDocument();
        for(Integer i = 1; i <= count; i++){
            String url = "http://www.ebook.ytzikao.me/data/ebook/zikao/jiaocai/" + bookCode + "/files/page/" + i + ".jpg";
            byte[] imageBytes = HttpUtils.getFor(url, byte[].class);
            PDImageXObject imageXObject = PDImageXObject.createFromByteArray(document, imageBytes, "图片加载失败");
            PDRectangle pdRectangle = new PDRectangle((float)(imageXObject.getWidth()), (float)(imageXObject.getHeight()));
            PDPage imagePage = new PDPage(pdRectangle);
            document.addPage(imagePage);
            PDPageContentStream contentStream = new PDPageContentStream(document, imagePage);
            contentStream.drawImage(imageXObject, 0, 0);
            contentStream.close();
            Log.getLogger().info("page " + i + " download succeed");
        }
        long spendTime = (System.currentTimeMillis() - startTime) / 1000;
        String fileName = diretory + "/" + bookCode + ".pdf";
        Log.getLogger().info("pdf for bookcode:" + bookCode + " download sucessFull! file saved in "+fileName+" spend time "+spendTime+"s.");
        document.save(fileName);
    }

}
