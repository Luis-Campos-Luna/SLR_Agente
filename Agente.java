/* Agent’s Inputs: A set of observations:DataSet will be hard coded; reuse 
the Data Set from Hands-on 2.An X value to predict Y. The Agent will read 
such  an X  value by using an Input Dialog Box (see jade’s bookTrading example).
Agent’s Outputs: Print regression equation: on terminal (cmd/bash) by replacing 
coefficients calculated values. The agent must allow to make single predictions,
by reading X values through its input dialog. 
@author Luis Isael Campos Luna 
Instituto tecnologico José Mario Molina Pasquel y Henríquez 
5 semestre, grupo B, T/M, 01/Oct/2021
 */
package AgenteSLR;

import java.util.Scanner;
import jade.core.Agent;
import javax.swing.JOptionPane;

public class Agente extends Agent {
    double[] x;
    double[] y;
    int n;          //número de datos
    public double a, b;    //pendiente y ordenada en el origen o b0 y b1
    Scanner teclado;

    @Override
    public void setup(){
        teclado = new Scanner(System.in);
        System.out.println("Recuerda que ¨x¨ es advertising y ¨y¨ es sales");
        /*System.out.println("Ingrese la cantidad de valores a ingresar: ");
        n=teclado.nextInt();*/
        n= Integer.parseInt(JOptionPane.showInputDialog (null,"Ingrese intervalo x: ",
        "Agente X",JOptionPane.QUESTION_MESSAGE));
        x = new double [n];
        y = new double [n];
        for (int i=0; i<n; i++){
            /*System.out.println("Ingrese intervalo x: ");
            x [i] = teclado.nextDouble();*/
            x [i]= Integer.parseInt(JOptionPane.showInputDialog (null,"Ingrese intervalo x: ",
        "Agente X",JOptionPane.QUESTION_MESSAGE));
        }
        for (int i=0; i<n; i++){
            /*System.out.println("Ingrese intervalo y: ");
            y [i] = teclado.nextDouble();*/
            y [i]= Integer.parseInt(JOptionPane.showInputDialog (null,"Ingrese intervalo y: ",
        "Agente Y",JOptionPane.QUESTION_MESSAGE));
        }
    }
    
    public Agente(double[] x, double[] y) {
        this.x=x;
        this.y=y;
    }
    public void Regresionlineal(){
        double pxy, sx, sy, sx2, sy2;
        pxy=sx=sy=sx2=sy2=0.0;
        for(int i=0; i<n; i++){
            sx+=x[i];
            sy+=y[i];
            sx2+=x[i]*x[i];
            sy2+=y[i]*y[i];
            pxy+=x[i]*y[i];
        }
        b=(n*pxy-sx*sy)/(n*sx2-sx*sx);
        a=(sy-b*sx)/n;
        JOptionPane.showMessageDialog(null,  "El resultado es:"+a+", "+b,"Concluido", JOptionPane.INFORMATION_MESSAGE);
    }
    public void Regression(){
        
        int X; //variable de Advertaising para sacar Sales
        double ŷ; 
        System.out.println("ŷ = B0 + B1 xi");//Regression equation 
        System.out.println("Ingresa x para sacar y"); //No se como hacer que salga bien la y
        X=teclado.nextInt();
        ŷ=a+b*X;
        System.out.println("y = "+ŷ); //Sales = y
    }
    public double correlation(){
        //Los valores medios
        double suma=0.0;
        for(int i=0; i<n; i++){
            suma+=x[i];
        }
        double mediaX=suma/n;

        suma=0.0;
        for(int i=0; i<n; i++){
            suma+=x[i];
        }
        double mediaY=suma/n;
        //Los coeficiente de correlación
        double pxy, sx2, sy2;
        pxy=sx2=sy2=0.0;
        for(int i=0; i<n; i++){
            pxy+=(x[i]-mediaX)*(y[i]-mediaY);
            sx2+=(x[i]-mediaX)*(x[i]-mediaX);
            sy2+=(y[i]-mediaY)*(y[i]-mediaY);
        }
        return pxy/Math.sqrt(sx2*sy2);
    }
}
