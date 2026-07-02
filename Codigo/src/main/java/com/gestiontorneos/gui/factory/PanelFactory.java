package com.gestiontorneos.gui.factory;

import javax.swing.*;

public interface PanelFactory {
    public JPanel crear(); //Metodo usado para crear un JPanel
    public JPanel crear(int alto, int ancho); //Metodo usado para crear un JPanel segun el alto y ancho dados
    public JPanel crear(int alto);//Metodo usado para crear un JPanel segun el alto dado
    public JPanel crear(int ancho;//Metodo usado para crear un JPanel segun el ancho dado
}
