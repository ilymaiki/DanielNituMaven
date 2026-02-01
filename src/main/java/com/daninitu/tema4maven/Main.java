package com.daninitu.tema4maven;

import com.github.lalyos.jfiglet.FigletFont; // JFiglet.
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory; // Lanterna.
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

// Paso 4. Banner ASCII (con JFiglet).
    String texto = "Daniel Nitu";
    String banner = FigletFont.convertOneLine(texto);
    System.out.println(banner);

// Paso 5. Contenido del guión.
    List<String> lineas = new ArrayList<>();
        lineas.add("Nombre completo: Daniel Nitu.");
        lineas.add("Mail: daninitu2006@gmail.com");
        lineas.add("Telefono: +34 123456789");
        lineas.add("Localidad: Gandia, Valencia");
        lineas.add("Curso actual: 1DAW-Semipresencial");
        lineas.add("Hobbies: Programación y Edición.");
        lineas.add("Cursos anteriores: FPB y SMX.");
        lineas.add("Metas actuales: seguir aprendiendo sobre programación.");
        lineas.add("Aptitudes: aplicado, trabajador, serio.");
        lineas.add("Experiencia laboral: prácticas en el extranjero.");

    // Paso 6: Inicializar Lanterna.
        Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        screen.setCursorPosition(null);

    // Paso 8: Animación (scroll hacia arriba)
        int yOffset = screen.getTerminalSize().getRows();

        while (yOffset > -lineas.size()) {
            drawFrame(screen, lineas, yOffset);
            Thread.sleep(100);
            yOffset--;
        }

    }

    // Paso 7: Dibujar el texto en la ventana (estático).
        private static void drawFrame (Screen screen, List<String> lines, int yOffset) throws IOException {

    // Variables.
        TerminalSize size = screen.getTerminalSize();
        int width = size.getColumns();
        int height = size.getRows();

        screen.clear();
        TextGraphics tg = screen.newTextGraphics();

            for (int i = 0; i < lines.size(); i++) {
                int y = yOffset + i;
            if (y < 0 || y >= height) continue;

            String line = lines.get(i);

        // Centrado horizontal.
            int x = Math.max(0, (width - line.length()) / 2);
            if (x >= width) continue;

        // Recorte simple si se sale por la derecha.
             String visible = (line.length() > width) ? line.substring(0, width) : line;
             tg.putString(x, y, visible);
            }

            screen.refresh();
    }
}
