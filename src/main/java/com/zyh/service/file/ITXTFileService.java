package com.zyh.service.file;

import com.zyh.domain.file.TXTFile;
import com.zyh.service.base.IBaseService;

public interface ITXTFileService extends IBaseService<TXTFile, Integer>{

    public TXTFile findByName();

    public TXTFile findDataById(Integer fileid);
}
