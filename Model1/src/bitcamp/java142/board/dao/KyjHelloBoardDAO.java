package bitcamp.java142.board.dao;

import java.util.ArrayList;

import bitcamp.java142.board.vo.KyjHelloBoardVO;

public interface KyjHelloBoardDAO {
	public int insertKyjHelloBoard(KyjHelloBoardVO kvo);
	public int updateKyjHelloBoard(KyjHelloBoardVO kvo);
	public int deleteKyjHelloBoard(KyjHelloBoardVO kvo);
	
	public ArrayList<KyjHelloBoardVO> selectKyjHelloBoard();
	public ArrayList<KyjHelloBoardVO> selectKyjBoard(KyjHelloBoardVO kvo);

} // end of KyjHelloBoardDAO
