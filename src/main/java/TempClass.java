import lombok.extern.slf4j.Slf4j;

import java.util.*;



@Slf4j
public class TempClass {


    public static void main(String[] args) {
        String expression = "e + 8 - a + 5";
        String[] evalvars = {"e"};
        int[] evalints = {1};
        List<String> result = basicCalculatorIV(expression, evalvars, evalints);
        System.out.println(result);
    }

    /*
    * 给定一个表达式如 expression = "e + 8 - a + 5" 和一个求值映射，
    *   如 {"e": 1}（给定的形式为 evalvars = ["e"] 和 evalints = [1]），
    *   返回表示简化表达式的标记列表，例如 ["-1*a","14"]
    * expression = 14 - a
    * */

    /*
    * 输入：expression = "e - 8 + temperature - pressure",
        evalvars = ["e", "temperature"], evalints = [1, 12]
    * ["-1*pressure","5"]
     * */

    static class Poly {
        Map<List<String>, Integer> map = new HashMap<>();

        public Poly() {}

        // 构造常数项
        public Poly(int intVal) {
            if (intVal != 0) {
                map.put(new ArrayList<>(), intVal);
            }
        }

        // 构造变量项
        public Poly(String varVal, int intVal) {
            if (intVal != 0) {
                List<String> vars = new ArrayList<>();
                vars.add(varVal);
                map.put(vars, intVal);
            }
        }

        // 加法
        public Poly add(Poly other) {
            Poly res = new Poly();
            res.map.putAll(this.map);
            for (var entry : other.map.entrySet()) {
                List<String> k = entry.getKey();
                int v = entry.getValue();
                res.map.put(k, res.map.getOrDefault(k, 0) + v);
                if (res.map.get(k) == 0) {
                    res.map.remove(k);
                }
            }
            return res;
        }

        // 减法   设置好值，用加法来算减法
        public Poly sub(Poly other) {
            Poly negOther = new Poly();
            for (var entry : other.map.entrySet()) {
                negOther.map.put(entry.getKey(), -entry.getValue());
            }
            return this.add(negOther);
        }

        // 乘法
        public Poly mul(Poly other) {
            Poly res = new Poly();
            for (var e1 : this.map.entrySet()) {
                for (var e2 : other.map.entrySet()) {
                    List<String> k1 = e1.getKey();
                    List<String> k2 = e2.getValue() == 0 ? new ArrayList<>() : e2.getKey();

                    // 合并变量并排序
                    List<String> newKey = new ArrayList<>(k1);
                    newKey.addAll(e2.getKey());
                    Collections.sort(newKey);

                    int newVal = e1.getValue() * e2.getValue();
                    res.map.put(newKey, res.map.getOrDefault(newKey, 0) + newVal);
                    if (res.map.get(newKey) == 0) {
                        res.map.remove(newKey);
                    }
                }
            }
            return res;
        }
    }

    public static List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        // 预处理求值映射
        Map<String, Integer> evalMap = new HashMap<>();
        for (int i = 0; i < evalvars.length; i++) {
            evalMap.put(evalvars[i], evalints[i]);
        }

        // 语法解析器（双栈法）
        Deque<Poly> polyStack = new ArrayDeque<>();
        Deque<Character> opStack = new ArrayDeque<>();

        int i = 0, n = expression.length();
        while (i < n) {
            char c = expression.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            }

            if (Character.isDigit(c)) {
                int num = 0;
                while (i < n && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                polyStack.push(new Poly(num));
            } else if (Character.isLowerCase(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < n && Character.isLowerCase(expression.charAt(i))) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                String var = sb.toString();
                if (evalMap.containsKey(var)) {
                    polyStack.push(new Poly(evalMap.get(var)));
                } else {
                    polyStack.push(new Poly(var, 1));
                }
            } else if (c == '(') {
                opStack.push(c);
                i++;
            } else if (c == ')') {
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    evaluate(polyStack, opStack);
                }
                opStack.pop(); // 弹出 '('
                i++;
            } else if (c == '+' || c == '-' || c == '*') {
                while (!opStack.isEmpty() && precedence(opStack.peek()) >= precedence(c)) {
                    evaluate(polyStack, opStack);
                }
                opStack.push(c);
                i++;
            }
        }

        while (!opStack.isEmpty()) {
            evaluate(polyStack, opStack);
        }

        Poly finalPoly = polyStack.pop();

        // 格式化输出与排序
        List<List<String>> keys = new ArrayList<>(finalPoly.map.keySet());
        keys.sort((a, b) -> {
            if (a.size() != b.size()) {
                return b.size() - a.size(); // 次数高的排前面
            }
            // 次数相同按字典序
            for (int k = 0; k < a.size(); k++) {
                int cmp = a.get(k).compareTo(b.get(k));
                if (cmp != 0) return cmp;
            }
            return 0;
        });

        List<String> res = new ArrayList<>();
        for (List<String> key : keys) {
            int coeff = finalPoly.map.get(key);
            if (coeff == 0) continue;

            StringBuilder sb = new StringBuilder();
            sb.append(coeff);
            for (String var : key) {
                sb.append("*").append(var);
            }
            res.add(sb.toString());
        }

        return res;
    }

    private static int precedence(char op) {
        if (op == '*') return 2;
        if (op == '+' || op == '-') return 1;
        return 0;
    }

    private static void evaluate(Deque<Poly> polyStack, Deque<Character> opStack) {
        Poly p2 = polyStack.pop();
        Poly p1 = polyStack.pop();
        char op = opStack.pop();

        if (op == '+') polyStack.push(p1.add(p2));
        else if (op == '-') polyStack.push(p1.sub(p2));
        else if (op == '*') polyStack.push(p1.mul(p2));
    }
}
