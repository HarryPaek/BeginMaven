/**
 * 
 */
package net.foundation.domain;

import java.util.Date;

/**
 * @author HarryPaek
 *
 */
public class BoardVO {
	private int    bno;
	private String title;
	private String content;
	private String writer;
	private Date   regdate;
	private int    viewcnt;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	@Override
	public String toString() {
		return String.format("BoardVO [bno=%d, title=%s, writer=%s]", bno, title, writer);
	}
}
