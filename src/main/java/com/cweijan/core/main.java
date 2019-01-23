package com.cweijan.core;

import com.cweijan.service.EbookService;

import java.io.IOException;

public class main{

    public static void main(String[] args) throws IOException{

        EbookService ebookService = new EbookService("D:/pdf");
//        ebookService.downloadPdf("02324", 166);
        ebookService.downloadPdf("04729", 329);

    }

}
