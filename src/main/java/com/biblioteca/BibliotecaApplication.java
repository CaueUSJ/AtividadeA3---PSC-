package com.biblioteca;


import com.biblioteca.view.LoginTela;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) {
            System.setProperty("java.awt.headless", "false");
            SpringApplication.run(BibliotecaApplication.class, args);
                
            // Instancia a interface gráfica após o contexto do Spring esta pronto
            javax.swing.SwingUtilities.invokeLater(() -> { new LoginTela(); } );
	}

}
