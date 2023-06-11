package com.blog.blog.serviceimpl;

import com.blog.blog.controller.LogController;
import com.blog.blog.service.FileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    static Logger logger= LogManager.getLogger(FileServiceImpl.class);


    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {


        logger.info("upload fileimpl start .....");
        //file name
        String name = file.getOriginalFilename();
        //abc.png
        
        //random name generate file

        String randomID = UUID.randomUUID().toString();
        String filename1 = randomID.concat(name.substring(name.lastIndexOf(".")));


        //full path

        String filepath= path + File.separator+filename1;

        //create folder if not created

        File f=new File(path);
        if(!f.exists())
        {
            f.mkdir();
        }

        //file copy

        Files.copy(file.getInputStream(), Paths.get(filepath));

        logger.info("exit upload fileimpl image.........");

        return filename1;
    }

    @Override
    public InputStream getResourse(String path, String filename) throws FileNotFoundException {


        String fullpath = path + File.separator + filename;

        InputStream is = new FileInputStream(fullpath);
        logger.info("get resource of fileimpl exit......");
        return is;
    }
}
