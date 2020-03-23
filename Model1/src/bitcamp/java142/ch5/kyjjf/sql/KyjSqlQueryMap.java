package bitcamp.java142.ch5.kyjjf.sql;

public abstract class KyjSqlQueryMap {
	
	public static final String KYJ_MEMBER_KMEM_CHABUN = 
				 "SELECT  /*+ INDEX_DESC (A SYS_C0011075) */ "
				+"NVL(MAX(SUBSTR(A.KMEM, 10)),0) + 1 COMMNO "
				+"FROM    KYJ_MEMBER A";
	
	public static String getInsertQuery(){
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO				\n");
		sb.append("	KYJ_MEMBER A(			\n");
		sb.append("A.KMEM					\n");
		sb.append(",A.KID					\n");
		sb.append(",A.KPW					\n");
		sb.append(",A.KNAME					\n");
		sb.append(",A.KHP					\n");
		sb.append(",A.KBIRTH				\n");
		sb.append(",A.KEMAIL				\n");
		sb.append(",A.KPOSTNO				\n");
		sb.append(",A.KJUSO					\n");
		sb.append(",A.KDELETEYN				\n");
		sb.append(",A.KINSERTDATE			\n");
		sb.append(",A.KUPDATEDATE)			\n");
		sb.append("VALUES(					\n");
		sb.append("	?						\n");
		sb.append(",?						\n");
		sb.append(",?						\n");
		sb.append(",?						\n");
		sb.append(",?						\n");
		sb.append(",?						\n");
		sb.append(",?						\n");
		sb.append(",?						\n");
		sb.append(",?						\n");
		sb.append(",'Y'						\n");
		sb.append(",SYSDATE					\n");
		sb.append(",SYSDATE)");
		
		return sb.toString();
	}
	public static String getUpdateQuery(){
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE  							\n");
		sb.append("	KYJ_MEMBER A					\n");
		sb.append("SET     A.KEMAIL = ?				\n");
		sb.append(", A.KUPDATEDATE=SYSDATE			\n");
		sb.append("WHERE   A.KNAME = ?				\n");
		sb.append("AND     A.KDELETEYN ='Y'			\n");

		return sb.toString();
	}
	public static String getDeleteQuery(){
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE  							\n");
		sb.append("	KYJ_MEMBER A					\n");
		sb.append("SET     A.KDELETEYN ='N'			\n");
		sb.append("WHERE   A.KNAME = ?				\n");
		sb.append("AND     A.KDELETEYN ='Y'			\n");
		return sb.toString();
	}
	public static String getSelectQuery(){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT					\n");
		sb.append("	A.KMEM      KMEM		\n");
		sb.append("	,A.KID      KID			\n");
		sb.append("	,A.KPW      KPW			\n");
		sb.append("	,A.KNAME    KNAME		\n");
		sb.append("	,A.KHP      KHP			\n");
		sb.append("	,A.KBIRTH   KBIRTH		\n");
		sb.append("	,A.KEMAIL   KEMAIL		\n");
		sb.append("	,A.KPOSTNO  KPOSTNO		\n");
		sb.append("	,A.KJUSO    KJUSO		\n");
		sb.append("	,A.KDELETEYN    KDELETEYN	\n");
		sb.append("	,TO_CHAR(A.KINSERTDATE, 'YYYY-MM-DD')  KINSERTDATE			\n");
		sb.append("	,TO_CHAR(A.KUPDATEDATE, 'YYYY-MM-DD')  KUPDATEDATE			\n");
		sb.append("FROM    KYJ_MEMBER A		\n");
		sb.append("ORDER BY A.KMEM DESC		");
		
		return sb.toString();
	}
	public static String getSearchQuery(){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT					\n");
		sb.append("	A.KMEM      KMEM		\n");
		sb.append("	,A.KID      KID			\n");
		sb.append("	,A.KPW      KPW			\n");
		sb.append("	,A.KNAME    KNAME		\n");
		sb.append("	,A.KHP      KHP			\n");
		sb.append("	,A.KBIRTH   KBIRTH		\n");
		sb.append("	,A.KEMAIL   KEMAIL		\n");
		sb.append("	,A.KPOSTNO  KPOSTNO		\n");
		sb.append("	,A.KJUSO    KJUSO		\n");
		sb.append("	,A.KDELETEYN    KDELETEYN	\n");
		sb.append("	,TO_CHAR(A.KINSERTDATE, 'YYYY-MM-DD')  KINSERTDATE			\n");
		sb.append("	,TO_CHAR(A.KUPDATEDATE, 'YYYY-MM-DD')  KUPDATEDATE			\n");
		sb.append("FROM    KYJ_MEMBER A		\n");
		sb.append("WHERE   A.KNAME = ?		\n");
		sb.append("AND     A.KDELETEYN = 'Y'");
		
		return sb.toString();
	}
	public static String getLikeSearchQuery(){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT					\n");
		sb.append("	A.KMEM      KMEM		\n");
		sb.append("	,A.KID      KID			\n");
		sb.append("	,A.KPW      KPW			\n");
		sb.append("	,A.KNAME    KNAME		\n");
		sb.append("	,A.KHP      KHP			\n");
		sb.append("	,A.KBIRTH   KBIRTH		\n");
		sb.append("	,A.KEMAIL   KEMAIL		\n");
		sb.append("	,A.KPOSTNO  KPOSTNO		\n");
		sb.append("	,A.KJUSO    KJUSO		\n");
		sb.append("	,A.KDELETEYN    KDELETEYN	\n");
		sb.append("	,TO_CHAR(A.KINSERTDATE, 'YYYY-MM-DD')  KINSERTDATE			\n");
		sb.append("	,TO_CHAR(A.KUPDATEDATE, 'YYYY-MM-DD')  KUPDATEDATE			\n");
		sb.append("FROM    KYJ_MEMBER A		\n");
		sb.append("WHERE   A.KNAME LIKE  ? ||'%'\n");
		sb.append("ORDER BY A. KMEM ASC		");

		return sb.toString();
	}
	
	public static String getLoginQuery(){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT						\n");
		sb.append("	A.KID      KID				\n");
		sb.append("	,A.KPW      KPW				\n");
		sb.append("	,A.KDELETEYN    KDELETEYN 	\n");
		sb.append("FROM    KYJ_MEMBER A			\n");
		sb.append("	WHERE   1=1		\n");
		sb.append("	AND     A.KDELETEYN = 'Y'	\n");
		sb.append("	AND     A.KID = ?			\n");
		sb.append("	AND     A.KPW = ?			\n");
		sb.append("ORDER BY A.KMEM ASC		");
		
		return sb.toString();
	}
} // end of KyjSqlQueryMap
