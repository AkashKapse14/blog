package com.blog.blog.serviceimpl;

import com.blog.blog.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        
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


        return filename1;
    }

    @Override
    public InputStream getResourse(String path, String filename) throws FileNotFoundException {


        String fullpath = path + File.separator + filename;

        InputStream is = new FileInputStream(fullpath);
        return is;
    }
}
