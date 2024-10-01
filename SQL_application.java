// ToDoリスト
// もともとチャットログ的なものにする予定だったのでテーブル関連chatになってます

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


	public class SQL_application {


		   public static void main(String[] args) throws Exception {
			      Scanner sc = new Scanner(System.in);

			   Connection con = null;
			   Statement  st01 = null;
		       Statement st02 = null;
			   ResultSet rs01;
			   ResultSet rs02 = null;
		       PreparedStatement ps = null;


			 //データベースに接続するために必要
		        String url = "jdbc:mysql://localhost/test_db";
		        String user = "root";
		        String password = "start01Sql";


		        // JDBCドライバの定義
		        Class.forName("com.mysql.cj.jdbc.Driver");

		        //mySQLに接続
		        con = DriverManager.getConnection(url,user,password);
		        st01 = con.createStatement();
			    st02 = con.createStatement();


		        // choiceの値で分岐させるのでとりあえず適当な値を入れておいてみる　do whileのほうが綺麗？
		        String choice = "empty^^";


		        
		        System.out.println("---ToDoリスト---");
	        	System.out.println("【現在のタスク】");
	        	rs01 = st01.executeQuery("SELECT * FROM chat");
			    while(rs01.next()) {
			    		System.out.println(rs01.getInt("chat_id")+" : "+ rs01.getString("chat_text"));
			        }
		        System.out.println("\n");
		        
		        
		        
		        
		        // 何するかの選択→分岐→ここに戻る　Eが入力されたら此処に戻ってきた時終了
		        while(!choice.equals("E") && !choice.equals("e")) {     // 〇〇以外もしくは〇〇以外　だから||にならない
			        System.out.println("■なにする？");
			        System.out.println("【I…追加　U…変更　D…完了(削除)　E…終了】");
			        choice = sc.next();

			    // こっから分岐
		        switch(choice) {


		        // 登録の場合　※怪しい※
		        case "I","i":
				      System.out.print("コメントを追加：");
				      String insert_text = sc.next();
				      String insert_sql = "INSERT INTO chat(chat_id,chat_text) value(?,?)";

				      rs02 = st02.executeQuery("SELECT COUNT(*) FROM chat");
					    while(rs02.next()) {
					    int insert_num = rs02.getInt(1) + 1 ;   // ※while挟まないと「Before start of result set」エラー。発生原因わからない

				            ps = con.prepareStatement(insert_sql);
				            ps.setInt(1, insert_num);
				            ps.setString(2, insert_text);
				            int insert_execution = ps.executeUpdate();
					    }
				        
			        	System.out.println("【現在のタスク】");
				        	rs01 = st01.executeQuery("SELECT * FROM chat");
						    while(rs01.next()) {    // ここを「SELECT chat_text FROM chat where chat_id=2」にしてみてもwhile挟まないとエラー吐いた
					    		System.out.println(rs01.getInt("chat_id")+" : "+ rs01.getString("chat_text"));
						        }
					        System.out.println("\n");
				        break;
				        

		        // 更新の場合
		        case "U","u":
			      System.out.print("何番目のタスクを編集する？：");
			      int b_change_text = sc.nextInt();
			      // 取得したchat_idのタスクを取得できてもよさげ　PreparedStatement増やさなきゃかもだからやらない
			      System.out.print("何に変える？：");
			      String a_change_text = sc.next();
			      
			      String update_sql = "UPDATE chat SET chat_text = ? WHERE chat_id = ?";
			        	ps = con.prepareStatement(update_sql);
			            ps.setInt(2, b_change_text);
			            ps.setString(1, a_change_text);

			            int update_execution = ps.executeUpdate();
				    
			        	System.out.println("【現在のタスク】");
				        rs01 = st01.executeQuery("SELECT * FROM chat");
					    while(rs01.next()) {
				    		System.out.println(rs01.getInt("chat_id")+" : "+ rs01.getString("chat_text"));
				        }
			        System.out.println("\n");
		        break;


		        // 削除の場合
		        case "D","d":
				      System.out.print("何番目のコメントを消す？：");
				      int delete_num = sc.nextInt();
				      String delete_sql = "DELETE FROM chat WHERE chat_id = ?";
				      ps = con.prepareStatement(delete_sql);
				      ps.setInt(1, delete_num);
				      int delete_execution = ps.executeUpdate();
				      
			        	System.out.println("【現在のタスク】");
						  rs01 = st01.executeQuery("SELECT * FROM chat");
					    while(rs01.next()) {
				    		System.out.println(rs01.getInt("chat_id")+" : "+ rs01.getString("chat_text"));
				        }
			        System.out.println("\n");
		        break;


				 // 終了
		        case "E","e":
		        	System.out.print("---終了---");
		        	break;


		        // 指定の文字以外を入力されたとき
		        default:
		        	System.out.println("メニューのなかからどれか選んでほしいな～～～\n全角だったら半角にしてね\n");
		        	break;

		        	}

		        // リソースの開放
		        }

		   }
	}
