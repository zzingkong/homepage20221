// 뮤직 테이블에있는 하나의 행을  MusicVo 객체하나에 담을 수 있다.

package egovframework.let.music.service;

public class MusicVO {
	
	private int musicId;
	private String musicTitle;
	private String listArtist;
	private String listName;
	private int recommenCo;
	public int getMusicId() {
		return musicId;
	}
	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}
	public String getMusicTitle() {
		return musicTitle;
	}
	public void setMusicTitle(String musicTitle) {
		this.musicTitle = musicTitle;
	}
	public String getListArtist() {
		return listArtist;
	}
	public void setListArtist(String listArtist) {
		this.listArtist = listArtist;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public int getRecommenCo() {
		return recommenCo;
	}
	public void setRecommenCo(int recommenCo) {
		this.recommenCo = recommenCo;
	}
		
}
