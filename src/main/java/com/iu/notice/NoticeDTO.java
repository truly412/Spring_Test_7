package com.iu.notice;

import java.util.List;

import com.iu.board.BoardDTO;
import com.iu.file.FileDTO;

public class NoticeDTO extends BoardDTO {
	
	private List<FileDTO> files;

	public List<FileDTO> getFiles() {
		return files;
	}

	public void setFiles(List<FileDTO> files) {
		this.files = files;
	}
}
