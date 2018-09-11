public class Interpretador {

    int interpreta (ArvoreSintatica arv) {
        if (arv instanceof Mult)
            return (interpreta(((Mult) arv).arg1) *
                    interpreta(((Mult) arv).arg2));

        if (arv instanceof Div)
            return (interpreta(((Div) arv).arg1 ) /
                    interpreta(((Div) arv).arg2));

        if (arv instanceof Soma)
            return (interpreta(((Soma) arv).arg1) +
                    interpreta(((Soma) arv).arg2));

        if (arv instanceof Sub)
            return (interpreta(((Sub) arv).arg1 ) -
                    interpreta(((Sub) arv).arg2));

        if (arv instanceof Num)
            return ((Num) arv).num;

        return -1;
    }

    public static void main(String[]args)
    {
        ArvoreSintatica arv;

        try{

            AnaliseLexica al = new AnaliseLexica(args[0]);
            Parser as = new Parser(al);

            arv = as.parseProg();

            Interpretador backend = new Interpretador();
            int resultado = backend.interpreta(arv);

            System.out.println(resultado);

        }catch(Exception e)
        {
            System.out.println("Erro de interpretação:\n" + e);
        }
    }
}
