package com.shsxt.ego.manager.service;

import com.shsxt.ego.model.PictureResult;
import org.springframework.web.multipart.MultipartFile;

public interface IFileOperationService {
    PictureResult uploadFile(MultipartFile file);
}
