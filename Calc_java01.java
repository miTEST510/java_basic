
// 初級①　計算機
// 数字・演算子・数字を入力して計算結果を表示

import java.util.Scanner;

public class Calc_java01 {

	public static void main(String[] args) {
	      Scanner sc = new Scanner(System.in); // 参考書ではscの部分「stdIn」になってたけどそうじゃなくてもいいっぽい

	      System.out.print("xの値：");
	      int x = sc.nextInt();

	      System.out.print("算出演算子(+-/*のどれかで)：");
	      String operator = sc.next();        // 算術演算子は……算術演算子として変数に格納できない……ッ！

	      System.out.print("yの値：");
	      int y = sc.nextInt();

	      if (operator.equals("+"))          // == を使って比較したら全部「算術演算子間違ってます」って言われた
	    	  System.out.println(x+y);      // Sring型はequalsを使って比較しとこ　nullは「nullが格納されてる変数はnullですか？」って聞いてから分岐すると良さげ
	      else if (operator.equals("-"))
	    	  System.out.println(x-y);
	      else if (operator.equals("*"))
	    	  System.out.println(x*y);
	      else if (operator.equals("/"))
	    	  System.out.println(x/y);      // 計算結果用の変数を用意しておいて、if文で格納する値を変える感じでも良さそう
	      else
	    	  System.out.println("算術演算子が間違っています");


	}

}
