package sprint4;

/*
Жители Алгосского архипелага придумали новый способ сравнения строк.
Две строки считаются равными, если символы одной из них можно заменить на символы другой так,
что первая строка станет точной копией второй строки. При этом необходимо соблюдение двух условий:

Порядок вхождения символов должен быть сохранён.
Одинаковым символам первой строки должны соответствовать одинаковые символы второй строки. Разным символам —– разные.
Например, если строка s = «abacaba», то ей будет равна строка t = «xhxixhx», так как все вхождения «a»
заменены на «x», «b» –— на «h», а «c» –— на «i». Если же первая строка s=«abc», а вторая t=«aaa», то строки
уже не будут равны, так как разные буквы первой строки соответствуют одинаковым буквам второй.

Формат ввода
В первой строке записана строка s, во второй –— строка t. Длины обеих строк не превосходят 106.
Обе строки содержат хотя бы по одному символу и состоят только из маленьких латинских букв.

Строки могут быть разной длины.

Формат вывода
Выведите «YES», если строки равны (согласно вышеописанным правилам), и «NO» в ином случае.

Ввод
mxyskaoghi
qodfrgmslc


Вывод
YES
 */
import java.io.*;
import java.util.HashMap;
public class H {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String str1 = reader.readLine();
            String str2 = reader.readLine();

            boolean res = isSameString(str1, str2);
            if (res) {
                writer.write("YES");
            } else {
                writer.write("NO");
            }
        }
    }

    private static boolean isSameString(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        //«abacaba», то ей будет равна строка t = «xhxixhx»
        /*
        aba
        xxx
         */
        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();
        for (int i = 0; i < charArray1.length; i++) {
            char char1 = charArray1[i];
            char char2 = charArray2[i];

            if (map1.containsKey(char1)) {
                if (char2 != map1.get(char1)) {
                    return false;
                }
            } else {
                map1.put(char1, char2);
            }

            if (map2.containsKey(char2)) {
                if (char1 != map2.get(char2)) {
                    return false;
                }
            } else {
                map2.put(char2, char1);
            }
        }

        return true;
    }
}
