
// 初級②　クイズ
// QAのリストからランダムに3つ問題を出して回答の成否を判定する

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class quiz_java02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String [][] qa_List = {        // 問題,答え の配列
				{"1日は何時間？半角数字で答えて","24"},
				{"太陽は東西南北どこから登る？","東"},
				{"バルト三国はエストニア、ラトビア、あと一か国は？ABCのいずれか（半角英字）で答えて\nA：ポーランド　B：リトアニア　C:ベラルーシ","B"},
				{"日本で一番高い山は？漢字で答えて","富士山"},
				{"都道府県の数は？半角数字で答えて","47"}
		};

		int qanum = qa_List.length -1;  // qaの数を数字に起こす　1からカウントなので-1にする

		int[] num = new int[3];     // 3つほしいって宣言
		ArrayList<Integer>random_list = new ArrayList<Integer>();
        for(int i = 0;i <= qanum; i++){
        	random_list.add(i);
        }

        Collections.shuffle(random_list);  // 数字のリストをランダムに並べ替える

        for(int i = 0;i < num.length; i++){  // 並べ方物を頭から取り出す
        	num[i]=random_list.get(i);
        	System.out.println(qa_List[num[i]][0]);  // ここから問題表示
        	System.out.print("回答を入力してください：");  // 上の行と一緒にかけるけど見づらいので改めて記述
        	String answer = sc.next();

        	if (answer.equals(qa_List[num[i]][1]))
        		System.out.println("◎正解◎\n");
        	else
        		System.out.println("×不正解×\n");
        }

	}

}
