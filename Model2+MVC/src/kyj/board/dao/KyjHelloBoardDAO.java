package kyj.board.dao;

import java.util.ArrayList;

import kyj.board.vo.KyjHelloBoardVO;

public interface KyjHelloBoardDAO {
	public int insertKyjHelloBoard(KyjHelloBoardVO kvo);
	public int updateKyjHelloBoard(KyjHelloBoardVO kvo);
	public int updateKyjHelloBoard_1(KyjHelloBoardVO kvo);
	public int deleteKyjHelloBoard(KyjHelloBoardVO kvo);
	
	public ArrayList<KyjHelloBoardVO> selectKyjHelloBoard();
	public ArrayList<KyjHelloBoardVO> selectKyjBoard(KyjHelloBoardVO kvo);
	
	//��й�ȣ �ߺ�üũ
	public int pwValCheck(KyjHelloBoardVO kvo);

} // end of KyjHelloBoardDAO
