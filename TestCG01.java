/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TestCG01;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;
//import java.awt.geom.*;
//import java.util.*;

public class TestCG01 extends JPanel { 
    private int lados = 5;
    private double angulo = 0;
    private double escala = 1.0;
    private double translacaoX = 0, translacaoY = 0;
    private double cisalhamentoX = 0, cisalhamentoY = 0;

    public TestCG01() {
        setPreferredSize(new Dimension(600, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
        g2.translate(w / 2 + translacaoX, h / 2 + translacaoY);

        // Aplicar transformações
        g2.scale(escala, escala);
        g2.shear(cisalhamentoX, cisalhamentoY);
        g2.rotate(Math.toRadians(angulo));

        // Criando o  poligono
        Polygon poligono = new Polygon();
        double raio = 100;
        for (int i = 0; i < lados; i++) {
            double theta = 2 * Math.PI * i / lados;
            int x = (int) (raio * Math.cos(theta));
            int y = (int) (raio * Math.sin(theta));
            poligono.addPoint(x, y);
        }

        g2.drawPolygon(poligono);
    }

    public void setLados(int l) {
        lados = l;
        repaint();
    }

    public void setAngulo(double a) {
        angulo = a;
        repaint();
    }

    public void setEscala(double e) {
        escala = e;
        repaint();
    }

    public void setTranslacao(double x, double y) {
        translacaoX = x;
        translacaoY = y;
        repaint();
    }

    public void setCisalhamento(double x, double y) {
        cisalhamentoX = x;
        cisalhamentoY = y;
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Visualizador do Polígono");
        TestCG01 viewer = new TestCG01();
        

        // painel 
        JPanel controles = new JPanel();
        controles.setLayout(new GridLayout(6, 2));
        controles.setBackground(Color.GREEN);

        // sliders e campos para controlar
        JSlider sliderLados = new JSlider(3, 100, 5);
        sliderLados.addChangeListener(e -> viewer.setLados(sliderLados.getValue()));
        controles.add(new JLabel("Lados:"));
        controles.add(sliderLados);

        JSlider sliderRotacao = new JSlider(0, 360, 0);
        sliderRotacao.addChangeListener(e -> viewer.setAngulo(sliderRotacao.getValue()));
        controles.add(new JLabel("Rotação:"));
        controles.add(sliderRotacao);

        JSlider sliderEscala = new JSlider(1, 300, 100);
        sliderEscala.addChangeListener(e -> viewer.setEscala(sliderEscala.getValue() / 100.0));
        controles.add(new JLabel("Escala: " ));
        controles.add(sliderEscala);

        JSlider sliderTransX = new JSlider(-200, 200, 0);
        sliderTransX.addChangeListener(e -> viewer.setTranslacao(sliderTransX.getValue(), viewer.translacaoY));
        controles.add(new JLabel("Translação X:"));
        controles.add(sliderTransX);

        JSlider sliderTransY = new JSlider(-200, 200, 0);
        sliderTransY.addChangeListener(e -> viewer.setTranslacao(viewer.translacaoX, sliderTransY.getValue()));
        controles.add(new JLabel("Translação Y:" ));
        controles.add(sliderTransY);

        JSlider sliderCisalhamento = new JSlider(-100, 100, 0);
        sliderCisalhamento.addChangeListener(e -> viewer.setCisalhamento(sliderCisalhamento.getValue() / 100.0, 0));
        controles.add(new JLabel("Cisalhamento X:" ));
        controles.add(sliderCisalhamento);

        frame.setLayout(new BorderLayout());
        frame.add(viewer, BorderLayout.CENTER);
        frame.add(controles, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setVisible(true);
    }
}
