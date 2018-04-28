package com.zyh.domain.file;

import java.io.Serializable;
import java.util.Date;

/**
 * TXT文件的数据库存储对象
 *
 * @author      1101399
 * @CreateDate  2018-4-12 下午5:08:45
 */
public class TXTFile implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 对于几百兆到2GB大小的文件，使用内存映射文件的话， 速度会块一些，但是内存映射由于映射的文件长度不能超
     * 过java中int类型的最大值，所以只能处理2GB以下的 文件。
     */
    private Integer fileId;// 文件id
    private String fileName;// 文件名称
    private String displayName;// 显示名称
    private String extension;// 扩展后缀名
    private String contentType;// 内容描述
    // 考虑是否将文件进行压缩方便上传数据库，①下载到web段或者是②调用到该URL的其他程序
    private String fileData;// 文件内容 存储
    private Integer fileSize;// 文件大小
    private Date createTime;// 上传时间

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
