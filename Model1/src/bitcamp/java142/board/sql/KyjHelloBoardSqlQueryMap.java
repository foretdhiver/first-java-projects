package bitcamp.java142.board.sql;

public abstract class KyjHelloBoardSqlQueryMap {
	
	public static final String KYJ_BOARD_KNO_CHAEBUN = 
			 "SELECT /*+ INDEX_DESC(A SYS_C0011080) */ "
			+"NVL(MAX(SUBSTR(A.KNO,2)),0)+1 COMMNO "
			+"FROM KYJHELLOBOARD A ";
	public static String getSearchQuery(){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT												\n");
		sb.append("A.KNO 					KNO,						\n");
		sb.append("A.KSUBJECT 				KSUBJECT,					\n");
		sb.append("A.KNAME					KNAME, 						\n");
		sb.append("A.KPW					KPW, 						\n");
		sb.append("A.KMEMO					KMEMO,						\n");
		sb.append("A.KDELETEYN				KDELETEYN, 					 \n");
		sb.append("TO_CHAR(A.KINSERTDATE, 'YYYY-MM-DD')	    KINSERTDATE, \n");
		sb.append("TO_CHAR(A.KUPDATEDATE, 'YYYY-MM-DD')		KUPDATEDATE  \n");
		sb.append("FROM KYJHELLOBOARD A						\n");
		sb.append("WHERE A.KNO= ?							\n");
		sb.append("AND   A.KDELETEYN='Y' 					\n");
		
		return sb.toString();
	}
	public static String getInsertQuery(){
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO 				\n");
		sb.append(" KYJHELLOBOARD A(        \n");
		sb.append("A.KNO					\n");
		sb.append(",A.KSUBJECT				\n");
		sb.append(",A.KNAME					\n");
		sb.append(",A.KPW					\n");
		sb.append(",A.KMEMO					\n");
		sb.append(",A.KDELETEYN				\n");
		sb.append(",A.KINSERTDATE			\n");
		sb.append(",A.KUPDATEDATE			\n");
		sb.append("	 				)	    \n");
		sb.append("VALUES(					\n");
		sb.append("	?						\n");
		sb.append(",?						\n");
		sb.append(",?						\n");
		sb.append(",?						\n");
		sb.append(",?						\n");
		sb.append(",?						\n");
		sb.append(",SYSDATE					\n");
		sb.append(",SYSDATE					\n");
		sb.append("		 )					  ");

		
		
		return sb.toString();
	}
	public static String getUpdateQuery(){
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE  KYJHELLOBOARD A 			\n");
		sb.append("SET     A.KSUBJECT = ?	 		\n");
		sb.append(" 	   ,A.KMEMO = ? 			\n");
		sb.append("        ,A.KUPDATEDATE=SYSDATE	\n");
		sb.append("WHERE   A.KNO= ? 				\n");
		sb.append("AND     A.KDELETEYN='Y' 			");
		
		return sb.toString();
	}

	public static String getDeleteQuery(){
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE  KYJHELLOBOARD A 			\n");
		sb.append("SET     A.KDELETEYN = 'N' 		\n");
		sb.append("WHERE   A.KNO= ? 				\n");
		sb.append("AND     A.KDELETEYN='Y' 			");
		
		return sb.toString();
	}
	
	public static String getSelectQuery(){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT												\n");
		sb.append("A.KNO 					KNO,						\n");
		sb.append("A.KSUBJECT 				KSUBJECT,					\n");
		sb.append("A.KNAME					KNAME, 						\n");
		sb.append("A.KPW					KPW, 						\n");
		sb.append("A.KMEMO					KMEMO,						\n");
		sb.append("A.KDELETEYN				KDELETEYN, 					 \n");
		sb.append("TO_CHAR(A.KINSERTDATE, 'YYYY-MM-DD')	    KINSERTDATE, \n");
		sb.append("TO_CHAR(A.KUPDATEDATE, 'YYYY-MM-DD')		KUPDATEDATE  \n");
		sb.append("FROM KYJHELLOBOARD A						\n");
		sb.append("WHERE 1=1								\n");
		sb.append("AND     A.KDELETEYN='Y' 					\n");
		sb.append("ORDER BY A.KNO DESC						 ");
		

		return sb.toString();
	}
} // end of KyjHelloBoardSqlQueryMap
