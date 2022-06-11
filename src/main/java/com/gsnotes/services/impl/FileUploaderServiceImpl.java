package com.gsnotes.services.impl;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.services.IFileUploaderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@Transactional
public class FileUploaderServiceImpl implements IFileUploaderService {

    private String dir="C:/Users/pc/OneDrive - Universite Abdelmalek Essaadi/Desktop/GI2/code";
    @Override
    public void uploadFile(MultipartFile file) {
        try {
            System.out.println(file.getOriginalFilename());
            Path copyLocation = Paths.get(dir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            file.getInputStream().close();
        } catch (Exception e) {
            System.out.print(e);
            throw new RuntimeException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }
    }

