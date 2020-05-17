package controllers.game;

import controllers.RandomController;
import controllers.view.MapPresenter;
import models.Environment;
import models.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class GameJFrame extends JFrame {

    private static final GameJFrame instance = new GameJFrame();

    private final HashMap<String, ImageIcon> MapTextures = new HashMap<>();

    private final CommandPanel commandPanel = new CommandPanel();

    private final Game game = new Game();
    private final CommandInterpreter commandInterpreter = new CommandInterpreter();

    BufferedImage image;

    private Tile activeTile = null;

    public Game getGame() {
        return game;
    }

    public void setActiveTile(Tile t) {
        activeTile = t;
    }

    private GameJFrame() {
        this.setContentPane(new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 800, 600, this);
            }
        });

        initTextures();
        initComponents();

        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                repaint();
            }

            public void windowIconified(WindowEvent e) {
                repaint();
            }

            public void windowDeiconified(WindowEvent e) {
                repaint();
            }

            public void windowActivated(WindowEvent e) {
                repaint();
            }

            public void windowDeactivated(WindowEvent e) {
                repaint();
            }

            public void windowStateChanged(WindowEvent e) {
                repaint();
            }

            public void windowGainedFocus(WindowEvent e) {
                repaint();
            }
        });

        this.setTitle("IceField");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void initTextures() {

        for(int i = 0; i < 7; i++)
        {
            MapTextures.put("stable_" + i, new ImageIcon("resources/stable_" + i + ".png"));
            MapTextures.put("stable_" + i + "_iglu", new ImageIcon("resources/stable_" + i + "_iglu.png"));
            MapTextures.put("stable_" + i + "_tent", new ImageIcon("resources/stable_" + i + "_tent.png"));
        }
        MapTextures.put("water", new ImageIcon("resources/water.png"));
        MapTextures.put("stable", new ImageIcon("resources/stable.png"));

        MapTextures.put("bear", new ImageIcon("resources/bear.png"));
        MapTextures.put("bear_active", new ImageIcon("resources/bear_active.png"));
        MapTextures.put("eskimo", new ImageIcon("resources/eskimo.png"));
        MapTextures.put("eskimo_active", new ImageIcon("resources/eskimo_active.png"));
        MapTextures.put("researcher", new ImageIcon("resources/researcher.png"));
        MapTextures.put("researcher_active", new ImageIcon("resources/researcher_active.png"));
    }

    public void CharacterOutInfo(String text) {
        this.text.setText(text);
        this.text.grabFocus();
    }

    public void OutputToTextBox(String text) {
        this.debug.setText(text);
        this.debug.grabFocus();
    }

    private final JTextArea text = new JTextArea("Csapattagok:\n  Ábrahám Dániel\n  Józsa György Bence\n  Kovács Marcell\n  Matyasi Lilla Nóra\n  Tóth Máté\n\nKonzulens:\n  Dr. Goldschmidt Balázs");
    private final JTextArea debug = new JTextArea("Debug info");
    private final JTextArea team = new JTextArea("team_rocket\n      v1.0");
    private final JLabel io = new JLabel("IO:");
    private final JTextField textBox = new JTextField("Filename Textbox");
    private final JButton load = new JButton("Load");
    private final JButton save = new JButton("Save");
    private final JLabel randomness = new JLabel("Randomness:   ");
    private final JButton random = new JButton("Random Off");
    private final JButton weather = new JButton("Simulate Weather");
    private final JLabel gameplay = new JLabel("Gameplay:");
    private final JButton special = new JButton("Use Special Ability");
    private final JButton craft = new JButton("Craft Signal Flare");
    private final JButton rescue = new JButton("Rescue");
    private final JButton snow = new JButton("Clear Snow");
    private final JButton unbury = new JButton("Unbury");
    private final JButton move = new JButton("Move");
    private final JButton next = new JButton("Next Character");

    public ImageIcon getTexture(String key) {
        return MapTextures.get(key);
    }

    void initComponents() {
        this.setResizable(false);
        this.setSize(930, 600);
        this.setLayout(null);
        this.setVisible(true);
        this.add(commandPanel);
        this.setIgnoreRepaint(true);

        commandPanel.setBounds(600, 0, 315, 600);

        io.setVisible(true);
        commandPanel.add(io);

        textBox.setVisible(true);
        commandPanel.add(textBox);

        load.setVisible(true);
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Environment.serializeRead(textBox.getText());
            }
        });
        commandPanel.add(load);

        save.setVisible(true);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Environment.serializeWrite(textBox.getText());
            }
        });
        commandPanel.add(save);

        randomness.setVisible(true);
        commandPanel.add(randomness);

        random.setVisible(true);
        random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.random();
                if(random.getText().equals("Random Off"))
                    random.setText("Random On");
                else if(random.getText().equals("Random On"))
                    random.setText("Random Off");
            }
        });
        commandPanel.add(random);

        weather.setVisible(true);
        weather.setEnabled(false);
        weather.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.simulateWeather();
            }
        });
        commandPanel.add(weather);

        gameplay.setVisible(true);
        commandPanel.add(gameplay);

        next.setVisible(true);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.NextCharacter();
            }
        });
        commandPanel.add(next);

        move.setVisible(true);
        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (activeTile != null) {
                    game.Move(activeTile);
                    activeTile = null;
                }
            }
        });
        commandPanel.add(move);

        unbury.setVisible(true);
        unbury.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.Unbury();
            }
        });
        commandPanel.add(unbury);

        snow.setVisible(true);
        snow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.clearSnow();
            }
        });
        commandPanel.add(snow);

        rescue.setVisible(true);
        rescue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (activeTile != null) {
                    game.Rescue(activeTile);
                    activeTile = null;
                }
            }
        });
        commandPanel.add(rescue);

        craft.setVisible(true);
        commandPanel.add(craft);
        craft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.CraftSignalFlare();
                }
            }
        );


        special.setVisible(true);
        special.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (activeTile != null) {
                    game.useSpecial(activeTile);
                    activeTile = null;
                }
            }
        });
        commandPanel.add(special);

        text.setVisible(true);
        text.setEditable(false);
        commandPanel.add(text);

        debug.setVisible(true);
        debug.setEditable(false);
        commandPanel.add(debug);
        team.setVisible(true);
        team.setEditable(false);
        commandPanel.add(team);

        RandomController.addListener(x -> weather.setEnabled(!x));
    }

    public void createBackground() {

        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics graph = image.createGraphics();
        graph.setColor(new Color(0, 148, 255));
        graph.fillRect(0, 0, 800, 600);
        graph.setColor(new Color(0, 0, 0));
        graph.translate(30, 30);
        MapPresenter.getInstance().paint(graph);
    }

    public void paint(Graphics g) {

        io.setBounds(10, 10, 20, 15);
        textBox.setBounds(5, 30, 305, 20);

        load.setBounds(5, 60, 150, 20);
        save.setBounds(160, 60, 150, 20);

        randomness.setBounds(10, 90, 150, 15);

        random.setBounds(5, 110, 150, 20);
        weather.setBounds(160, 110, 150, 20);

        gameplay.setBounds(10, 140, 150, 15);

        next.setBounds(5,160, 150, 20);
        move.setBounds(160,160, 150, 20);
        unbury.setBounds(5,190, 150, 20);
        snow.setBounds(160,190, 150, 20);
        rescue.setBounds(5,220, 150, 20);
        craft.setBounds(160,220, 150, 20);
        special.setBounds(80,250, 150, 20);


        text.setBounds(5,280, 400, 145);
        debug.setBounds(5, 430, 400,90);
        team.setBounds(130,520, 400, 230);

        createBackground();
        super.paint(g);
    }

    public static GameJFrame getInstance() {
        return instance;
    }

    public void showItemDialog(String itemName) {
        ItemDialog dialog = new ItemDialog(this, itemName);
    }

    public void showEndGameDialog() {
        EndGameDialog dialog = new EndGameDialog(this, "End Game");
    }

    public void showWinGameDialog() {
        EndGameDialog dialog = new EndGameDialog(this, "Game Won");
    }

}
