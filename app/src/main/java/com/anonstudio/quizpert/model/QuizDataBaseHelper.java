package com.anonstudio.quizpert.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import androidx.annotation.Nullable;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.ARTS_TABLE_NAME;
import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.GEO_TABLE_NAME;
import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.HIS_TABLE_NAME;
import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.MATH_TABLE_NAME;
import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.MUS_TABLE_NAME;
import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.SCI_TABLE_NAME;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.*;

public class QuizDataBaseHelper extends SQLiteOpenHelper {

    private static final String DataBase = "quizster.db";
    private static final int db_version = 1;

    private static String TABLE_NAME;


    private HashMap<Integer,Questions> holdQuestions = new HashMap<>();




    private SQLiteDatabase db;

    public QuizDataBaseHelper(@Nullable Context context) {
        super(context, DataBase, null, db_version);
    }

    private void setTable(int table_num) {

        switch(table_num) {
            case 0:
                TABLE_NAME = QuizConsts.QuestionsTable.SCI_TABLE_NAME;
                break;
            case 1:
                TABLE_NAME = QuizConsts.QuestionsTable.MUS_TABLE_NAME;
                break;
            case 2:
                TABLE_NAME = QuizConsts.QuestionsTable.HIS_TABLE_NAME;
                break;
            case 3:
                TABLE_NAME = QuizConsts.QuestionsTable.MATH_TABLE_NAME;
                break;
            case 4:
                TABLE_NAME = QuizConsts.QuestionsTable.ARTS_TABLE_NAME;
                break;
            case 5:
                TABLE_NAME = QuizConsts.QuestionsTable.GEO_TABLE_NAME;
                break;

        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        //6 - hardcoded for number of quizzes in game so far
        for (int i = 0; i < 6; i++) {
            setTable(i);
            final String Sql_create_ques_table = "CREATE TABLE " +
                    TABLE_NAME + " ( " +
                    QuizConsts.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    QuizConsts.QuestionsTable.COLUMN_QUESTIONS + " TEXT, " +
                    QuizConsts.QuestionsTable.COLUMN_ANSWER1 + " TEXT, " +
                    QuizConsts.QuestionsTable.COLUMN_ANSWER2 + " TEXT, " +
                    QuizConsts.QuestionsTable.COLUMN_ANSWER3 + " TEXT, " +
                    QuizConsts.QuestionsTable.COLUMN_ANSWER_ID + " TEXT," +
                    QuizConsts.QuestionsTable.CATEGORY + " TEXT" +
                    ")";
            db.execSQL(Sql_create_ques_table);
            fillQuestionsTable(i);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //hardcoded number 6 for number of tables created
        for (int i = 0; i < 6; i++) {
           setTable(i);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

    }

    //Science Quiz Questions
    private void science_quiz() {
        //SCIENCE QUESTIONS

        // CHAPTER 1 - SPACE
        holdQuestions.put(1000,new Questions("Which planet resides closest to the Sun?", "Saturn","Mars","Mercury","Mercury",chapter1));
        holdQuestions.put(1001,new Questions("What is the estimated speed of light in a vacuum?", "186,282 miles ps","47,000 miles ps","299,792 miles ps","186,282 miles ps",chapter1));
        holdQuestions.put(1002,new Questions("Which galaxy does the Earth inhabit?", "Milky Way","Andromeda","Triangulum","Milky Way",chapter1));
        holdQuestions.put(1003,new Questions("Which is the oldest planet in our solar system?", "Earth","Jupiter","Saturn","Jupiter",chapter1));
        holdQuestions.put(1004,new Questions("What is the most abundant element in the universe?", "Hydrogen","Oxygen","Nitrogen","Hydrogen",chapter1));
        holdQuestions.put(1005,new Questions("How many elements exist on the periodic table?", "117","118","116","118",chapter1));
        holdQuestions.put(1006,new Questions("What is the name of the Red Planet?", "Mars","Jupiter","Saturn","Mars",chapter1));
        holdQuestions.put(1007,new Questions("How many phases of the Moon are there?", "2","8","6","8",chapter1));
        holdQuestions.put(1008,new Questions("How many planets are there in our solar system?", "8","9","7","8",chapter1));

        holdQuestions.put(1009,new Questions("How many Earth days does Mercury take to make one full rotation?", "59","87","34","59",chapter1));
        holdQuestions.put(1010,new Questions("What is the shape of the Earth?", "Circle","Oblate Sphere","Sphere","Oblate Sphere",chapter1));
        holdQuestions.put(1011,new Questions("What gives the planets their rounded shape?", "Gravity","That's just how they are","Dark Matter","Gravity",chapter1));
        holdQuestions.put(1012,new Questions("Which planet has the most moons?", "Saturn","Jupiter","Mars","Saturn",chapter1));
        holdQuestions.put(1013,new Questions("Which two planets does not have any moon?", "Venus and Jupiter","Mercury and Venus","Neptune and Mercury","Mercury and Venus",chapter1));
        holdQuestions.put(1014,new Questions("An exoplanet is any planet...", "outside our solar system","within our solar system","that orbits moons","outside our solar system",chapter1));
        holdQuestions.put(1015,new Questions("Mercury, Venus, Earth and Mars are....?", "Inner Planets","Outer Planets","Exoplanets","Inner Planets",chapter1));
        holdQuestions.put(1016,new Questions("Which planet is the largest in our solar system?", "Mercury","Jupiter","Saturn","Jupiter",chapter1));

        holdQuestions.put(1017,new Questions("Which planet does the moon Titan belong to?", "Saturn","Jupiter","Mars","Saturn",chapter1));
        holdQuestions.put(1018,new Questions("What causes the existence of mass in fundamental/elementary particles?", "Higgs Field","Gravity","God Particle","Higgs Field",chapter1));
        holdQuestions.put(1019,new Questions("Which of the following is NOT a fundamental particle?", "Leptons","Quarks","Protons","Protons",chapter1));
        holdQuestions.put(1020,new Questions("What is the dominant gas in the Mars atmosphere?", "Carbon Dioxide","Carbon Monoxide","Argon","Carbon Dioxide",chapter1));
        holdQuestions.put(1021,new Questions("How are neutron stars formed?", "Collapse of a massive star","Death of a planet","Clouds of Dust","Collapse of a massive star",chapter1));
        holdQuestions.put(1022,new Questions("Which of the following is an ice planet?", "Neptune","Jupiter","Venus","Neptune",chapter1));
        holdQuestions.put(1023,new Questions("Which one is the largest known galaxy in the universe?", "Andromeda","Large Magellanic Cloud","IC 1101","IC 1101",chapter1));
        holdQuestions.put(1024,new Questions("What is the most outer region of our solar system?", "Oort Cloud","Kuiper Belt","Asteroid Belt","Oort Cloud",chapter1));

        holdQuestions.put(1025,new Questions("Which of the following is the densest planet in our solar system?", "Mars","Earth","Mercury","Earth",chapter1));
        holdQuestions.put(1026,new Questions("Which of the following is a dwarf planet?", "Pluto","Mars","Titan","Pluto",chapter1));
        holdQuestions.put(1027,new Questions("What is the approximate distance between the Sun and Earth?", "93 million miles","43 million miles","186 million miles","93 million miles",chapter1));
        holdQuestions.put(1028,new Questions("The dwarf planet Pluto has __ moons?", "7","5","0","5",chapter1));
        holdQuestions.put(1029,new Questions("What is the name of the largest known star in the universe?", "Proxima Centauri","Our Sun","Stephenson 2-18","Stephenson 2-18",chapter1));
        holdQuestions.put(1030,new Questions("Phobos and Deimos are the Moons of which planet?", "Mars","Saturn","Jupiter","Mars",chapter1));



        // HARD CHAPTER 1 - SPACE
        holdQuestions.put(1800,new Questions("Which planet has no moons?", "Uranus","Venus","Mars","Venus",hardChap1));
        holdQuestions.put(1801,new Questions("Which planet contains the largest circular storm known as the Great Red Spot in our solar system?", "Saturn","Venus","Jupiter","Jupiter",hardChap1));
        holdQuestions.put(1802,new Questions("A Mars day is approximately 39 minutes longer than a Earth day. A Mars year is equivalent to how many Earth days?", "687 days","366 days","412 days","687 days",hardChap1));
        holdQuestions.put(1803,new Questions("The Andromeda galaxy belongs to which type of galaxy?", "Spiral","Elliptical","Irregular","Spiral",hardChap1));
        holdQuestions.put(1804,new Questions("Which star is closest to our sun?", "Barnard's Star","Proxima Centauri","Rigil Kentaurus","Proxima Centauri",hardChap1));

        holdQuestions.put(1805,new Questions("What is the second most dominant gas found in the Mars atmosphere?", "Argon","Oxygen","Nitrogen","Nitrogen",hardChap1));
        holdQuestions.put(1806,new Questions("Besides Venus, what other planet has no moons?", "Mercury","Neptune","Jupiter","Mercury",hardChap1));
        holdQuestions.put(1807,new Questions("The Magellanic clouds are...", "irregular galaxies","spiral galaxies","elliptical galaxies","irregular galaxies",hardChap1));
        holdQuestions.put(1808,new Questions("What is the name of the outer boundary of the sun's magnetic field?", "Heliopause","Termination Shock","Bow Shock","Heliopause",hardChap1));
        holdQuestions.put(1809,new Questions("A pulsar is a...", "neutron star.","white dwarf star.","red giant star.","neutron star.",hardChap1));

        // CHAPTER 2 - PLANTS
        holdQuestions.put(2000,new Questions("What is the most common way plants access nitrogen from the air?", "by their leaves","by bacteria in soil","by their stems","by bacteria in soil",chapter2));
        holdQuestions.put(2001,new Questions("Plants intake carbon dioxide through their... ", "Leaves","Roots","Stems","Leaves",chapter2));
        holdQuestions.put(2002,new Questions("Which gas is mainly responsible for the growth of almost all plant life on Earth?", "Carbon Dioxide","Oxygen","Argon","Carbon Dioxide",chapter2));

        holdQuestions.put(2003,new Questions("Plants absorb carbon dioxide from the air through the...", "stomata","roots","leaf veins","stomata",chapter2));
        holdQuestions.put(2004,new Questions("What is the reproductive part of a plant?", "root","flower","stem","flower",chapter2));
        holdQuestions.put(2005,new Questions("Micro-Organisms act on dead plants and animals to create: ?", "Sand","Humus","Food","Humus",chapter2));
        holdQuestions.put(2006,new Questions("Pollen is produced in the: ", "Filament","Anther","Pistil","Anther",chapter2));
        holdQuestions.put(2007,new Questions("Which part of the plant takes in water from the soil?", "Stems","Roots","Leaves","Roots",chapter2));

        holdQuestions.put(2008,new Questions("What ingredients are needed for photosynthesis?", "H20 and CO2","H2O and O2","O2 and CO2","H2O AND CO2",chapter2));
        holdQuestions.put(2009,new Questions("What is the reproductive part of a plant?", "root","flower","stem","flower",chapter2));
        holdQuestions.put(2010,new Questions("Micro-Organisms act on dead plants and animals to create: ?", "Sand","Humus","Food","Humus",chapter2));
        holdQuestions.put(2011,new Questions("Pollen is produced in the: ", "Filament","Anther","Pistil","Anther",chapter2));
        holdQuestions.put(2012,new Questions("Which part of the plant takes in water from the soil?", "Stems","Roots","Leaves","Roots",chapter2));

        holdQuestions.put(2013,new Questions("Which part of the plant carries water through the plant?", "Xylem","Phloem","Epidermis","Xylem",chapter2));
        holdQuestions.put(2014,new Questions("What distinguishes a plant cell from an animal cell?", "Nucleus","Cell Wall, Chloroplasts, Vacuoles","Cell Membrane","Cell Wall, Chloroplasts, Vacuoles",chapter2));
        holdQuestions.put(2015,new Questions("Carrots are orange in color because they...", "contain carotene.","are not exposed to sunlight","are exposed to organisms in the soil","contain carotene.",chapter2));
        holdQuestions.put(2016,new Questions("During photosynthesis plants absorb ", "C02","N02","C0","C02",chapter2));
        holdQuestions.put(2017,new Questions("A seed can germinate in the absence of", "Light","Moisture","Temperature","Light",chapter2));

        holdQuestions.put(2018,new Questions("Curcumin is isolated from", "Turmeric","Onion","Garlic","Turmeric",chapter2));
        holdQuestions.put(2019,new Questions("Aerobes and Anaerobes degrade organic through the process of ?", "composting","manuring","digesting","composting",chapter2));
        holdQuestions.put(2020,new Questions("Which of the following is rich in iron?", "Apple","Orange","Mango","Apple",chapter2));
        holdQuestions.put(2021,new Questions("Flower less plants are termed", "Bryophytes","Thallopytes","Cryptogams","Cryptogams",chapter2));
        holdQuestions.put(2022,new Questions("Flower emit fragrance to ", "attract insects","keep away pests","purify the air","attract insects",chapter2));
        holdQuestions.put(2023,new Questions("Imbibition in plants is achieved through", "Absorption","Diffusion","Dilution","Absorption",hardChap2));

        // HARD CHAPTER 2
        holdQuestions.put(2800,new Questions("Which of these are non flowering plants?", "Dandelion","Fern","Oak Tree","Fern",hardChap2));
        holdQuestions.put(2801,new Questions("A perennial plant is a plant that is expected to live __________ and older.", "a year","two years","six months","two years",hardChap2));
        holdQuestions.put(2802,new Questions("What attribute should you use to tell the age of a tree?", "Height of tree","Rings on Trunk","Width of tree","Rings on Trunk",hardChap2));
        holdQuestions.put(2803,new Questions("A Deciduous tree loses its leaves at the end of the growing season. Which is not a deciduous tree?", "Cedar","Maple","Oak","Cedar",hardChap2));
        holdQuestions.put(2804,new Questions("A plant that has seeds but no flowers and fruits?", "Bryophytes","Mosses","Gymnosperms","Gymnosperms",hardChap2));

        holdQuestions.put(2805,new Questions("Double fertilization is the characteristic of...", "Algae","Fungi","Angiosperms","Angiosperms",hardChap2));
        holdQuestions.put(2806,new Questions("Plants reproducing by spores are grouped under...", "Thallophytes","Sporophytes","Cryptogams","Cryptogams",hardChap2));
        holdQuestions.put(2807,new Questions("Which tissue in the leaf absorbs the most light for photosynthesis?", "Upper epidermis","Palisade mesophyll","Spongy mesophyll","Palisade mesophyll",hardChap2));
        holdQuestions.put(2808,new Questions("Which useful energy transfer or conversion happens overall in photosynthesis?", "Light → chemical","Chemical → light","Light → heat","Light → chemical",hardChap2));
        holdQuestions.put(2809,new Questions("Which mineral ions are needed for chlorophyll in plants?", "Calcium","Magnesium","Copper","Magnesium",hardChap2));


        // CHAPTER 3 - CHEMISTRY
        holdQuestions.put(3000,new Questions("What do we call a scientist who studies Chemistry?", "Chemist","Khemist","Cemist","Chemist",chapter3));
        holdQuestions.put(3001,new Questions("The heaviest naturally ocurring element is _________", "Thorium","Uranium","Mercury","Uranium",chapter3));
        holdQuestions.put(3002,new Questions("When does the ph value indicate an acidic solution? ", ">7","<7","9","<7",chapter3));
        holdQuestions.put(3003,new Questions("Who is NOT associated with chemistry?", "Antoine Lavoisier","John Dalton","Nicolaus Copernicus","Nicolaus Copernicus",chapter3));
        holdQuestions.put(3004,new Questions("Which one is NOT a metal?", "Nickel","Gold","Graphite","Graphite",chapter3));
        holdQuestions.put(3005,new Questions("Which is NOT a pure element?", "Argon","Aluminium","glass","glass",chapter3));
        holdQuestions.put(3006,new Questions("What is the most common Hydrogen isotope?", "Protium","Deuterium","Tritium","Protium",chapter3));
        holdQuestions.put(3007,new Questions("What is the chemical name for water?", "Dihydrogen Monoxide","Dihydrogen Dioxide","Hydrogen Chloride","Dihydrogen Monoxide",chapter3));


        holdQuestions.put(3008,new Questions("The Hydrogen bomb is based on the principle of _________", "Nuclear Fission","Nuclear Fusion","Artificial Radioactivity","Nuclear Fusion",chapter3));
        holdQuestions.put(3009,new Questions("Which is the purest form of iron?", "wrought iron","nickel steel","steel","wrought iron",chapter3));
        holdQuestions.put(3010,new Questions("Which one of the following is a mixture", "milk","mercury","sodium","milk",chapter3));
        holdQuestions.put(3011,new Questions("Which one of the following is NOT a mixture", "cement","juice","water","water",chapter3));
        holdQuestions.put(3012,new Questions("Which is a solution?", "salt water","water","mixture","salt water",chapter3));
        holdQuestions.put(3013,new Questions("Milk is a poor source of ", "Calcium","Vitamin C","Protein","Vitamin C",chapter3));
        holdQuestions.put(3014,new Questions("Which scientist stated that matter can be converted into energy?", "Einstein","Boyle","Lavoisier","Einstein",chapter3));
        holdQuestions.put(3015,new Questions("Permanent Hardness of water can be removed by adding", "Bleaching powder","Washing soda","Chlorine ","Washing Soda",chapter3));


        holdQuestions.put(3016,new Questions("An element is a pure substance that is made entirely from one type of _________.", "atom","fluid","electron","atom",chapter3));
        holdQuestions.put(3017,new Questions("Which is NOT a mixture?", "gasoline","juice","sulphur","sulphur",chapter3));
        holdQuestions.put(3018,new Questions("Which is a mixture?", "diamond","baking soda","gunpowder","gunpowder",chapter3));
        holdQuestions.put(3019,new Questions("Which is NOT a solvent?", "Methanol","Ethanol","Argon","Argon",chapter3));
        holdQuestions.put(3020,new Questions("Which is a compound?", "Methane","H2O","Carbon","Carbon",chapter3));
        holdQuestions.put(3021,new Questions("An isotope is two or more atoms of an element which has the same number of protons but with different numbers of ________.", "electrons","quarks","neutrons","neutrons",chapter3));
        holdQuestions.put(3022,new Questions("Which is NOT an isotope of Carbon?", "Carbon-13","Carbon-14","Carbon-19","Carbon-19",chapter3));
        holdQuestions.put(3023,new Questions("A proton is a positively charged subatomic particle which is found in the _________ of an atom.", "nucleus","jelly","cloud","nucleus",chapter3));
        holdQuestions.put(3024,new Questions("An electron is viewed as having a ________ charge.", "negative","positive","neutral","negative",chapter3));

        holdQuestions.put(3025,new Questions("How many elements exist on the periodic table?", "117","118","116","118",chapter3));
        holdQuestions.put(3026,new Questions("An ion is a charged atom or molecule because the number of electrons do not equal the number of ________ in the atom or molecule.?", "neutrons","quarks","protons","protons",chapter3));
        holdQuestions.put(3027,new Questions("Which is an anion?", "Oxide","Chlorine","Nitrogen","Oxide",chapter3));
        holdQuestions.put(3028,new Questions("How many protons are there in the Carbon element?", "8","6","12","6",chapter3));
        holdQuestions.put(3029,new Questions("How many protons are there in the Silicon element?", "14","12","15","14",chapter3));
        holdQuestions.put(3030,new Questions("How many oxygen elements are there in water?", "2","4","1","1",chapter3));
        holdQuestions.put(3031,new Questions("What is the common name for Dihydrogen Monoxide?", "water","hydrogen peroxide","bauxite","water",chapter3));
        holdQuestions.put(3032,new Questions("A molecule is an electrically neutral group of two or more _______ held together by chemical bonds.", "neutrons","pieces","atoms","atoms",chapter3));
        holdQuestions.put(3033,new Questions("What is the boiling point of water?", "100 C","120 C","90 C","100 C",chapter3));

        // HARD CHAPTER 3
        holdQuestions.put(3800,new Questions("The second most abundant metal in the earth’s crust is", "silicon","aluminium","iron","",hardChap3));
        holdQuestions.put(3801,new Questions("Fatty foods becomes rancid due to the process of...", "oxidation","reduction","hydrogenation","",hardChap3));
        holdQuestions.put(3802,new Questions("In an oxidation reaction electrons are...", "similar.","lost.","gained.","lost.",hardChap3));
        holdQuestions.put(3803,new Questions("What mineral present in bananas make them slightly radioactive?", "potassium","calcium","magnesium","potassium",hardChap3));
        holdQuestions.put(3804,new Questions("Which of the following indicates a chemical change?", "Rusting","Melting","Heating","Rusting",hardChap3));

        holdQuestions.put(3805,new Questions("Which particles are assumed to hold the nucleons together?", "mesons","neutrons","positrons","mesons",hardChap3));
        holdQuestions.put(3806,new Questions("The inherited traits of an organism are controlled by...", "DNA molecules","RNA molecules","enzymes","DNA molecules",hardChap3));
        holdQuestions.put(3807,new Questions("Which of the following is the most malleable metal?", "silver","iron","gold","gold",hardChap3));
        holdQuestions.put(3808,new Questions("Which gas is used to ripen green fruit?", "acetylene","ethylene","ethane","ethylene",hardChap3));
        holdQuestions.put(3809,new Questions("The mineral containing both magnesium and calcium is...", "dolomite","magnesite","calcite","dolomite",hardChap3));


        //CHAPTER 4 - SCIENTISTS
        holdQuestions.put(4000,new Questions("Which scientist wrote the Theory of Relativity?", "Einstein","Newton","Boyle","Einstein",chapter4));
        holdQuestions.put(4001,new Questions("Which scientist discovered how gravity works?", "Newton","Feynman","Pasteur","Newton",chapter4));
        holdQuestions.put(4002,new Questions("Which scientist showed that oxygen was important to combustion?", "Curie","Lavoisier","Mendel","Lavoisier",chapter4));
        holdQuestions.put(4003,new Questions("Which scientist was also a famous artist?", "Da Vinci","Faraday","Boyle","Da Vinci",chapter4));
        holdQuestions.put(4004,new Questions("Who was the first female Nobel Prize winner?", "Curie","Lovelace","Franklin","Curie",chapter4));
        holdQuestions.put(4005,new Questions("Who wrote the Philosophiæ Naturalis Principia Mathematica?", "Newton","Leibniz","Planck","Newton",chapter4));
        holdQuestions.put(4006,new Questions("Albert Einstein is considered a...", "theoretical physicist","philosopher","astronomer","theoretical physicist",chapter4));
        holdQuestions.put(4007,new Questions("Who created the Theory of Evolution?", "Darwin","Bell","Edison","Darwin",chapter4));

        holdQuestions.put(4008,new Questions("Frederick Banting and Charles Best isolated the hormone ________ to successfully treat diabetes.", "insulin","clomiphene","gonadotropin","insulin",chapter4));
        holdQuestions.put(4009,new Questions("Which scientist discovered Electromagnetic Induction?", "Nicholas Callan","Joseph Henry","Michael Faraday","Michael Faraday",chapter4));
        holdQuestions.put(4010,new Questions("What two elements did Marie Curie and Pierre Curie discover?", "polonium and radium","radium and thorium","cesium and thallium","polonium and radium",chapter4));
        holdQuestions.put(4011,new Questions("Who discovered Thermionic Emission?", "Issac Newton","Thomas Edison","Max Planck","Thomas Edison",chapter4));
        holdQuestions.put(4012,new Questions("Who discovered the Quantum theory?", "Max Planck","Albert Einstein","Enrico Fermi","Max Planck",chapter4));
        holdQuestions.put(4013,new Questions("Who discovered Law of Motion?", "Issac Newton","Ernest Rutherford","Charles-Augustin de Coulomb","Issac Newton",chapter4));
        holdQuestions.put(4014,new Questions("Which astronomer before Galileo postulated Heliocentrism?", "Ptolemy","Aristotle","Nicolaus Copernicus","Nicolaus Copernicus",chapter4));
        holdQuestions.put(4015,new Questions("Who is credited with the invention of the modern periodic table?", "Mendeleev","Mendel","Nobel","Mendeleev",chapter4));

        holdQuestions.put(4016,new Questions("Who discovered the Theory of Electromagnetic Radiation?", "James Maxwell","Heinrich Hertz","Max Planck","James Maxwell",chapter4));
        holdQuestions.put(4017,new Questions("Who discovered the mass of an electron?", "Albert Einstein","J.J. Thomson","James Maxwell","J.J. Thomson",chapter4));
        holdQuestions.put(4018,new Questions("Who discovered protons?", "Ernest Rutherford","Niels Bohr","Jonas Salk","Ernest Rutherford",chapter4));
        holdQuestions.put(4019,new Questions("Who discovered electrons?", "J.J. Thomson","Niels Bohr","Enrico Fermi","J.J. Thomson",chapter4));
        holdQuestions.put(4020,new Questions("Alexander Fleming discovered the world's first antibiotic substance, ___.", "penicillin","amoxicillin","doxycycline ","penicillin",chapter4));
        holdQuestions.put(4021,new Questions("What do Biologists study?", "life","societies","neurons","life",chapter4));
        holdQuestions.put(4022,new Questions("What do Botanists study?", "plants","amphibians","reptiles","plants",chapter4));
        holdQuestions.put(4023,new Questions("What do Entomologists study?", "animals","insects","amphibians","insects",chapter4));

        // HARD CHAPTER 4
        holdQuestions.put(4800,new Questions("Which scientist invented the barometer?", "J. J. Thomson","August Kekulé","Evangelista Torricelli","Evangelista Torricelli",hardChap4));
        holdQuestions.put(4801,new Questions("Which scientist discovered X-Rays?", "Emil Fischer","Wilhelm Roentgen","Harold Urey","Wilhelm Roentgen",hardChap4));
        holdQuestions.put(4802,new Questions("Which scientist discovered the element thallium?", "Henry Cavendish","William Crookes","Joseph Priestley","William Crookes",hardChap4));
        holdQuestions.put(4803,new Questions("Who invented the diesel engine?", "Rudolf Diesel","Stan Diesel","Bill Diesel","Rudolf Diesel",hardChap4));
        holdQuestions.put(4804,new Questions("Which explosive was invented by Alfred Nobel?", "Dynamite","Atomic bomb","Tsar Bomba","Dynamite",hardChap4));

        holdQuestions.put(4805,new Questions("Which scientist is credited with the invention of the World Wide Web?", "Bob Kahn","Vint Cerf","Tim Berners-Lee","Tim Berners-Lee",hardChap4));
        holdQuestions.put(4806,new Questions("What did French physician René Laënnec invent?", "Endoscope","Stethoscope","Otoscope","Stethoscope",hardChap4));
        holdQuestions.put(4807,new Questions("Which inventor built the first steam engine?", "Richard Trevithick","Robert Fulton","Thomas Savery","Thomas Savery",hardChap4));
        holdQuestions.put(4808,new Questions("What did Chester F. Carlson invent?", "Xerography","X-Rays","Cardiography","Xerography",hardChap4));
        holdQuestions.put(4809,new Questions("Who discovered the process called embryonic induction?", "Viktor Hamburger","Willem Einthoven","Hans Spemann","Hans Spemann",hardChap4));

        //CHAPTER 5 - Geology
        holdQuestions.put(5000,new Questions("What is the third most common gas found in the air we breathe?", "Hydrogen","Nitrogen","Argon","Argon",chapter5));
        holdQuestions.put(5001,new Questions("What are the two most abundant elements in nebula (gas clouds) in the universe?", "hydrogen and helium","oxygen and silicon","nitrogen and oxygen","hydrogen and helium",chapter5));
        holdQuestions.put(5003,new Questions("The layer that separates the Earth's crust from its core is the ____________", "mantle","lithosphere","magma layer","mantle",chapter5));
        holdQuestions.put(5004,new Questions("What drives the Earth's internal heat engine?", "solar","radioactivity","volcanoes","radioactivity",chapter5));
        holdQuestions.put(5005,new Questions("The age of the Earth is currently thought to be about...", "6000 years old","6 billion years old","4.5 billion years old","4.5 billion years old",chapter5));
        holdQuestions.put(5006,new Questions("When did geologists develop the theory of plate tectonics?", "1960s","1800s","1920s","1960s",chapter5));
        holdQuestions.put(5007,new Questions("How thick is the Earth's lithosphere in kilometers?", "50 - 100km","5 - 10km","100 - 200km","50 - 100km",chapter5));
        holdQuestions.put(5008,new Questions("How many approximate plates does the Earth's lithosphere consist of?", "10","24","12","12",chapter5));

        holdQuestions.put(5009,new Questions("Which of the following is NOT a type of plate boundary?", "convergent","divergent","both options","both options",chapter5));
        holdQuestions.put(5010,new Questions("New seafloor is created at a ", "mid-ocean ridge","deep sea trench","subduction zone","mid-ocean ridge",chapter5));
        holdQuestions.put(5011,new Questions("Approximately how deep (below sea level) are deep-sea trenches", "10km","5km","100km","10km",chapter5));
        holdQuestions.put(5012,new Questions("Volcanism is associated with which of the following types of plate boundaries?", "convergent","divergent","both options","both options",chapter5));
        holdQuestions.put(5013,new Questions("The Andes Mountains of South America are a result of which type of plate boundary?", "convergent","divergent","transform","convergent",chapter5));
        holdQuestions.put(5014,new Questions("What is the name of the large supercontinent that existed 200 million years ago when all of the continents were together?", "Pangaea","San Andreas","Andian","Pangaea",chapter5));
        holdQuestions.put(5015,new Questions("The process by which an originally homogeneous Earth developed a dense core and a light crust is called:", "differentiation","metamorphism","accretion","differentiation",chapter5));
        holdQuestions.put(5016,new Questions("The asthenosphere is...", "cool and strong","hot and strong","hot and weak","hot and weak",chapter5));

        holdQuestions.put(5017,new Questions("What is the approximate distance from the Earth's surface to its center?", "4,000 miles","1,000 miles","40,000 miles","4,000 miles",chapter5));
        holdQuestions.put(5018,new Questions("What drives plate tectonics?", "thermal convection","thermal conduction","solar energy","thermal convection",chapter5));
        holdQuestions.put(5019,new Questions("The Earth's magnetic field reverses itself roughly every ", "5,000 years","50 million years","500,000 years","500,000 years",chapter5));
        holdQuestions.put(5020,new Questions("The lithosphere includes", "crust and upper mantle","outer and inner core","outer core and lower mantle","crust and upper mantle",chapter5));
        holdQuestions.put(5021,new Questions("What region of the Earth takes up the greatest volume?", "mantle","inner core","outer core","mantle",chapter5));
        holdQuestions.put(5022,new Questions("The mantle consists mainly of ", "ultramafic rocks","basaltic rocks","gabbroic rocks","ultramafic rocks",chapter5));
        holdQuestions.put(5023,new Questions("What element makes up most of the Earth's core?", "iron","nitrogen","silicon","iron",chapter5));
        holdQuestions.put(5024,new Questions("The boundary between the inner core and the outer core lies at a depth of ______ kilometers", "5100","1000","4900","5100",chapter5));


        holdQuestions.put(5025,new Questions("The oceanic crust consists mainly of _________.", "basaltic rocks","granitic rocks","gabbroic rocks","basaltic rocks",chapter5));
        holdQuestions.put(5026,new Questions("The continental crust consists mainly of _________.", "granitic rocks","ultramafic rocks","basaltic rocks","granitic rocks",chapter5));
        holdQuestions.put(5027,new Questions("At what temperature do materials loose their permanent magnetism?", "550 degrees C","100 degrees C","250 degrees C","550 degrees C",chapter5));
        holdQuestions.put(5028,new Questions("Where is the Earth's magnetic field generated?", "in the outer core","in the inner core","in the mantle","in the outer core",chapter5));
        holdQuestions.put(5029,new Questions("The mechanical transfer of heat by vibration of atoms and molecules is called __________ ", "conduction","radiation","convection","conduction",chapter5));
        holdQuestions.put(5030,new Questions("Continental crust can be up to ______ kilometers thick", "10","65","100","65",chapter5));
        holdQuestions.put(5031,new Questions("Which of the planets is not geologically active?", "Mars","Mercury","Venus","Mercury",chapter5));
        holdQuestions.put(5032,new Questions("Oxygen built up in the Earth's atmosphere because organisms employed...", "photosynthesis","bacteria","carbon dioxide","photosynthesis",chapter5));

        // HARD CHAPTER 5
        holdQuestions.put(5800,new Questions("Which of these is not part of Earth’s crust?", "sima","sial","ionosphere","ionosphere",hardChap5));
        holdQuestions.put(5801,new Questions("What is diatomaceous earth made of?", "humus","fossilized organisms","lime","fossilized organisms",hardChap5));
        holdQuestions.put(5802,new Questions("How old is the sunlight when it reaches Earth?", "8 minutes","6 minutes","10 minutes","8 minutes",hardChap5));
        holdQuestions.put(5803,new Questions("What is the reflectivity of Earth’s surface called?", "reflectivity effect","zero effect","albedo effect","albedo effect",hardChap5));
        holdQuestions.put(5804,new Questions("What is Earth’s predominant environment?", "grass","desert","ocean","ocean",hardChap5));

        holdQuestions.put(5805,new Questions("How much of the Earth is covered with oceans?", "71%","65%","81%","71%",hardChap5));
        holdQuestions.put(5806,new Questions("Which of the following is not a part of Earth?", "mantle","litosphere","troposphere","litosphere",hardChap5));
        holdQuestions.put(5807,new Questions("Fossils of burrows and footprints are known as...", "trace fossils","body fossils","weak fossils","trace fossils",hardChap5));
        holdQuestions.put(5808,new Questions("Which of the following is the fundamental constituent of humic coal?", "Maceral","Kerogen","Lithotype","Maceral",hardChap5));
        holdQuestions.put(5809,new Questions("The Permian was a geologic...", "eon","peroid","epoch","peroid",hardChap5));

        //CHAPTER 6 - PHYSICS
        holdQuestions.put(6000,new Questions("Which is the weakest force in nature?", "electromagnetic force","weak nuclear force","gravity","gravity",chapter6));
        holdQuestions.put(6001,new Questions("Pierre Gassendi, observed gunfire from a distance in order to measure the speed of...?", "sound","light","atmospheric interference","sound",chapter6));
        holdQuestions.put(6002,new Questions("What did physicist J.J. Thomson discover in the 1890s when he analyzed cathode rays and concluded that they were made up of charged “corpuscles”?", "ions","electrons","photons","electrons",chapter6));
        holdQuestions.put(6003,new Questions("What reaction involving atomic nuclei is the source of the Sun’s energy?", "nuclear fission","nuclear fusion","ionization","nuclear fusion",chapter6));
        holdQuestions.put(6004,new Questions("What is the unit of measure for cycles per second?", "hertz","ampere","ohm","hertz",chapter6));
        holdQuestions.put(6005,new Questions("What is the SI unit of an electric charge?", "coulomb","ampere","watt","coulomb",chapter6));
        holdQuestions.put(6006,new Questions("Which type of electromagnetic radiation has the shortest wavelength?", "gamma ray","microwave","radio wave","gamma ray",chapter6));
        holdQuestions.put(6007,new Questions("What is the capacitance of a capacitor when one coulomb of electricity changes the potential between the plates by one volt?", "one farad","one henry","one amp","one farad",chapter6));

        holdQuestions.put(6008,new Questions("Triblogy is the study of the interaction of...?", "sliding surfaces","matter","particles","sliding surfaces",chapter6));
        holdQuestions.put(6009,new Questions("Who discovered the rotating magnetic field?", "Nikola Tesla","Pierre Curie","Paul Langevin","Nikola Tesla",chapter6));
        holdQuestions.put(6010,new Questions("What term defines work done per unit of time?", "magnetic flux","electric current","power","power",chapter6));
        holdQuestions.put(6012,new Questions("What term refers to the change of velocity with time?", "acceleration","distance","motion","acceleration",chapter6));
        holdQuestions.put(6013,new Questions("What is the flow of electric charge that periodically reverses direction?", "eddy current","alternating current","direct current","alternating current",chapter6));
        holdQuestions.put(6014,new Questions("The thought experiment known as Schrödinger’s cat postulates that a cat can be in two states at the same time. What are those two states?", "alive or dead","moving or stationary","awake or asleep","alive or dead",chapter6));
        holdQuestions.put(6015,new Questions("What is the internal force per unit area of a body that arises from externally applied forces?", "stress","action","moment","stress",chapter6));
        holdQuestions.put(6016,new Questions("A candela is the SI unit for what?", "luminous intensity","inductance","radioactivity","luminous intensity",chapter6));

        holdQuestions.put(6017,new Questions("What is the unit of magnetic induction?", "newton","mole","tesla","tesla",chapter6));
        holdQuestions.put(6018,new Questions("What is the formula for density?", "d = M/V","d = M + V","d = M - V","d = M/V",chapter6));
        holdQuestions.put(6019,new Questions("Why was Ernest Orlando Lawrence awarded the Nobel Prize in Physics in 1939?", "discovery of the multiverse","invention of hydrogen bomb","invention of cyclotron","invention of cyclotron",chapter6));
        holdQuestions.put(6020,new Questions("What is used to measure wind speed?", "barometer","altimeter","anemometer","anemometer",chapter6));
        holdQuestions.put(6021,new Questions("Who formulated the second law of thermodynamics?", "Ernst Mach","Rudolf Clausius","Ludwig Boltzmann","Rudolf Clausius",chapter6));
        holdQuestions.put(6022,new Questions("What is the rate of acceleration of gravity at the Earth’s surface?", "11.2m/s/s (36.7 feet/s/s)","9.8m/s/s (32 feet/s/s)","7.8m/s/s (25.6 feet/s/s)","9.8m/s/s (32 feet/s/s)",chapter6));
        holdQuestions.put(6023,new Questions("Which of the following is not a scalar quantity?", "mass","force","volume","force",chapter6));
        holdQuestions.put(6024,new Questions("What is Newton's first law of motion about?", "inertia","momentum","work","inertia",chapter6));

        holdQuestions.put(6025,new Questions("Ohm's law describes the relationship between what?", "current, voltage, resistance","frequency and velocity","current and velocity","current, voltage, resistance",chapter6));
        holdQuestions.put(6026,new Questions("The first law of thermodynamics is also known by what name?", "conservation of energy","conservation of charge","conservation of mass","conservation of energy",chapter6));
        holdQuestions.put(6027,new Questions("What material emits light when exposed to radiation?", "phosphor","melanophore","chromophore","phosphor",chapter6));
        holdQuestions.put(6028,new Questions("Who first discovered the neutron?", "James Chadwick","Otto Frisch","Wolfgang Pauli","James Chadwick",chapter6));
        holdQuestions.put(6029,new Questions("Who wrote the Principia?", "Issac Newton","Marie Curie","Thomas Edison","Issac Newton",chapter6));
        holdQuestions.put(6030,new Questions("What is the unit of measurement of the electromotive force?", "volt","watt","pascal","volt",chapter6));
        holdQuestions.put(6031,new Questions("What is the other name of strong force?", "nuclear force","magnetic force","electrical force","nuclear force",chapter6));
        holdQuestions.put(6032,new Questions("What is the change in the direction of a wave with a change in the medium called?", "resonance","refraction","reflection","refraction",chapter6));

        // HARD CHAPTER 6
        holdQuestions.put(6800,new Questions("Young's modulus is the property of...", "solid only","gas only","solid and gas","solid only",hardChap6));
        holdQuestions.put(6801,new Questions("Product of Force and Velocity is called...", "power","work","energy","power",hardChap6));
        holdQuestions.put(6802,new Questions("Energy possessed by a body in motion is called...", "potential energy","kinetic energy","mechanical energy","kinetic energy",hardChap6));
        holdQuestions.put(6803,new Questions("Which law is also called the law of inertia?", "None of these","Newton's First Law","Newton's Third Law","Newton's First Law",hardChap6));
        holdQuestions.put(6804,new Questions("A jet engine works on the principle of conservation of", "Linear momentum","Angular momentum","Energy Mass","Linear momentum",hardChap6));

        holdQuestions.put(6805,new Questions("Bolometer is used to measure...", "Frequency","Velocity","Temperature","Velocity",hardChap6));
        holdQuestions.put(6806,new Questions("What is the unit of Astronomical Distance?", "Light Year","Angstrom","Lux","Light Year",hardChap6));
        holdQuestions.put(6807,new Questions("The unit of power of lens is...", "Diopter","Metre","Centimeter","Diopter",hardChap6));
        holdQuestions.put(6808,new Questions("The SI unit for measuring work and energy is...", "Joule","Watt","Ohm","Joule",hardChap6));
        holdQuestions.put(6809,new Questions("If we add all the colors of rainbow, the resultant color will be?", "white","black","indigo","white",hardChap6));


        //CHAPTER 7 - RANDOM SCIENCE
        holdQuestions.put(7000,new Questions("Which of these is used to make plastic?", "sand","oil","metal","oil",chapter7));
        holdQuestions.put(7001,new Questions("Which of these elements can eventually become a diamond?", "carbon","thallium","sulfur","carbon",chapter7));
        holdQuestions.put(7002,new Questions("What element is used, with iron, to make steel?", "carbon","aluminum","chromium","carbon",chapter7));
        holdQuestions.put(7003,new Questions("What material is made up of cement and water?", "steel","concrete","gypsum","concrete",chapter7));
        holdQuestions.put(7004,new Questions("Which is an essential ingredient for gunpowder?", "potassium nitrate","thyme","calcium carbonate","potassium nitrate",chapter7));
        holdQuestions.put(7005,new Questions("What is the history of the evolution of a species or group?", "biology","phylogeny","genetics","phylogeny",chapter7));
        holdQuestions.put(7006,new Questions("Which biologist and geneticist deduced that the sex of an individual is determined by a particular chromosome?", "Nettie Stevens","Theodor Schwann","Daniel Mazia","Nettie Stevens",chapter7));
        holdQuestions.put(7007,new Questions("What is another name for the so-called sea wasp, which can be extremely dangerous to humans?", "box jellyfish","sting ray","squid","box jellyfish",chapter7));

        holdQuestions.put(7008,new Questions("What are produced in response to foreign substances called antigens?", "lymphocytes","antibodies","toxins","antibodies",chapter7));
        holdQuestions.put(7009,new Questions("What is a sea cucumber?", "seaweed","a type of vegetable","a marine invertebrate","a marine invertebrate",chapter7));
        holdQuestions.put(7010,new Questions("Chlorella algae has been studied as a food source because it is rich in which nutrients?", "Vitamin C","proteins","carbohydrates","proteins",chapter7));
        holdQuestions.put(7011,new Questions("What substance acts as a catalyst for biological reactions in living organisms?", "RNA","DNA","enzyme","enzyme",chapter7));
        holdQuestions.put(7012,new Questions("In which biological laboratory technique are fragments of tissue from an animal or plant transferred to an artificial environment where they can continue to survive and function?", "cytogenesis","tissue culture","cytoplasmic streaming","tissue culture",chapter7));
        holdQuestions.put(7013,new Questions("What term is given to the emission of light by living organisms such as a fireflies or bacteria?", "radioluminescence","photoluminescence","bioluminescence","bioluminescence",chapter7));
        holdQuestions.put(7014,new Questions("What is a cowrie?", "a marine smail","a flower","a baby cow","a marine smail",chapter7));
        holdQuestions.put(7015,new Questions("How many pairs of chromosomes are found in the human body?", "23","25","24","23",chapter7));

        holdQuestions.put(7016,new Questions("What is salmonella?", "a fish","an insect","bacteria","bacteria",chapter7));
        holdQuestions.put(7017,new Questions("How many pairs of chromosomes are found in the human body?", "amino acids","carbohydrates","enzymes","amino acids",chapter7));
        holdQuestions.put(7018,new Questions("What type of plankton is the primary food source, either directly or indirectly, of all sea organisms?", "phytoplankton","holoplankton","zooplankton","phytoplankton",chapter7));
        holdQuestions.put(7019,new Questions("Which of these amino acids, first isolated from gelatin, is the simplest?", "glycine","tyrosine","alanine","glycine",chapter7));
        holdQuestions.put(7020,new Questions("Which of these crabs is largest?", "hermit crab","Tasmanian crab","Alaskan king crab","Tasmanian crab",chapter7));
        holdQuestions.put(7021,new Questions("Which do not reproduce via parthenogenesis (reproduction without fertilization)?", "butterflies","honeybees","aphids","butterflies",chapter7));
        holdQuestions.put(7022,new Questions("Which animal is a carnivorous scavenger?", "lion","hyena","gaur","hyena",chapter7));
        holdQuestions.put(7023,new Questions("Which vitamin is water soluble as opposed to fat soluble?", "Vitamin D","Vitamin C","Vitamin E","Vitamin C",chapter7));

        holdQuestions.put(7024,new Questions("Which kingdom do mushrooms belong to?", "Fungi","Protists","Plants","Fungi",chapter7));
        holdQuestions.put(7025,new Questions("Who was the first to observe “animalcules,” the tiny cells we now call bacteria and protozoans?", "Antonie van Leeuwenhoek","Louis Pasteur","Marcello Malpighi","Antonie van Leeuwenhoek",chapter7));
        holdQuestions.put(7026,new Questions("What does an undertaker use to seal the eyes and lips of the deceased?", "cyanoacrylate glue","thread","oil","cyanoacrylate glue",chapter7));
        holdQuestions.put(7027,new Questions("Which dinosaur was a chicken-size predator?", "Spinosaurus ","Compsognathus","Deinonychus","Compsognathus",chapter7));
        holdQuestions.put(7028,new Questions("What is the basic unit of measurement for volume in the metric system?", "Liter","Kilogram","Ounce","Liter",chapter7));
        holdQuestions.put(7029,new Questions("Of the various types of light in natural sunlight, which one causes sunburn?", "ultraviolet","infrared","X-rays","ultraviolet",chapter7));
        holdQuestions.put(7030,new Questions("What is the study of animal behaviour called?", "Ethology","Biology","Zoology","Ethology",chapter7));

        holdQuestions.put(7031,new Questions("Thanatology is the study of...", "life","self healing","death","death",chapter7));
        holdQuestions.put(7032,new Questions("Most extrasolar planets are discovered when they pass in front of their star and dim its light. This dimming is called what?", "a phase","a transit","a refracted illusion","a transit",chapter7));
        holdQuestions.put(7033,new Questions("The first extrasolar planets were found around which unusual type of star?", "a blue star","a pulsar","a dwarf star","a pulsar",chapter7));
        holdQuestions.put(7034,new Questions("The place in a planetary system where liquid water can survive on a planet's surface is called what?", "Habitable Zone","Water Zone","Aqueous Region","Habitable Zone",chapter7));
        holdQuestions.put(7035,new Questions("Which of these chemical elements is heavier than iron?", "gold","carbon","potassium","gold",chapter7));
        holdQuestions.put(7036,new Questions("In physics, for every action there is an equal and opposite what?", "reaction","distraction","impaction","reaction",chapter7));
        holdQuestions.put(7037,new Questions("Which of these particles was discovered by J.J. Thompson?", "electron","proton","neutron","proton",chapter7));
        holdQuestions.put(7038,new Questions("Which of these chemicals help fruit to ripen?", "Methane","Ethylene","Carbon Dioxide","Ethylene",chapter7));

        // HARD CHAPTER 7
        holdQuestions.put(7800,new Questions("Which of the following is a large blood vessel that carries blood away from the heart?", "Vein","Artery","Capillary","Artery",hardChap7));
        holdQuestions.put(7801,new Questions("Fungi are plants that lack:", "Chlorophyll","None of these","Carbon dioxide","Chlorophyll",hardChap7));
        holdQuestions.put(7802,new Questions("Which blood vessels have the smallest diameter?", "Arterioles","Capillaries","Venules","Capillaries",hardChap7));
        holdQuestions.put(7803,new Questions("Oncology is the study of:", "Birds","Cancer","Mammals","Cancer",hardChap7));
        holdQuestions.put(7804,new Questions("Ornithology is the study of:", "birds","bones","noise","birds",hardChap7));

        holdQuestions.put(7805,new Questions("Fathometer is used to measure:", "Earthquakes","Ocean Depth","Sound Intensity","Ocean Depth",hardChap7));
        holdQuestions.put(7806,new Questions("The Chemical evolution of life was proposed by:", "Oparin","Newland","Darwin","Oparin",hardChap7));
        holdQuestions.put(7807,new Questions("Who discovered the magnetic field of current?", "Benjamin Franklin","Charles de Coulomb","Hans Oersted","Hans Oersted" ,hardChap7));
        holdQuestions.put(7808,new Questions("What is the SI unit of resistivity?", "Ohm-metre","Watt","Volt","Ohm-metre",hardChap7));
        holdQuestions.put(7809,new Questions("Semi-conductors are used for making:", "solar cells","toasters","room heaters","solar cells",hardChap7));

        //CHAPTER 8 - QUANTUM MECHANICS
        holdQuestions.put(8000,new Questions("Radiation and Matter have properties both of particles and of waves. What is this called?", "Particle Waves","Entanglement","Wave-particle duality","Wave-particle duality",chapter8));
        holdQuestions.put(8001,new Questions("Max Planck’s great discovery was that radiation energy is emitted in packets that he called what?", "Quanta","Gamma rays","Wave functions","Quanta",chapter8));
        holdQuestions.put(8002,new Questions("When two particles are entangled and it is observed that one has its spin up, how long does it take for the other’s spin to be down?", "Instantaneously","3 milliseconds","1 nanosecond","Instantaneously",chapter8));
        holdQuestions.put(8003,new Questions("In the Heisenberg uncertainty principle, which two measurable properties of a particle cannot be observed precisely at the same time?", "Position and Momentum","Speed and Size","Torque and Energy","Position and Momentum",chapter8));
        holdQuestions.put(8004,new Questions("Niels Bohr used quantum mechanics to describe which element?", "Thallium","Hydrogen","Carbon","Hydrogen",chapter8));
        holdQuestions.put(8005,new Questions("The Dirac equation shows that every particle has what?", "A wave function","An antiparticle","A duality","An antiparticle",chapter8));
        holdQuestions.put(8006,new Questions("What is the angular momentum of a particle called?", "Spin","Rotational Swirl","Orbital","Spin",chapter8));
        holdQuestions.put(8007,new Questions("What was the first antiparticle to be discovered?", "Antineutrino","Antiproton","Positron","Positron",chapter8));
        holdQuestions.put(8008,new Questions("Which of these is NOT a part of the four fundamental forces?", "Gravity","Strong nuclear force","Momentum","Momentum",chapter8));

        holdQuestions.put(8009,new Questions("Who first described \"energy quanta\"?", "Albert Einstein","Erwin Schrödinger","Max Planck","Max Planck",chapter8));
        holdQuestions.put(8010,new Questions("What does the energy of a photon depend on?", "Speed","Wavelength","Intensity","Wavelength",chapter8));
        holdQuestions.put(8011,new Questions("Which subatomic particle is negatively charged?", "electron","proton","neutron","electron",chapter8));
        holdQuestions.put(8012,new Questions("Which subatomic particle is positively charged?", "proton","neutron","electron","proton",chapter8));
        holdQuestions.put(8013,new Questions("What is inside the nucleus of the atom?", "electrons and protons","protons and neutrons","neutrons and electrons","protons and neutrons",chapter8));
        holdQuestions.put(8014,new Questions("How many quantum numbers are there?", "3","4","6","4",chapter8));
        holdQuestions.put(8015,new Questions("The quantum mechanical model describes electrons as...", "Waves","Particles","Matter waves","Matter waves",chapter8));
        holdQuestions.put(8016,new Questions("Any two electrons occupying the same orbital must have ___________ spins.", "opposite","identical","positive","opposite",chapter8));

        holdQuestions.put(8017,new Questions("How many spin values are possible for an electron?", "2","4","6","2",chapter8));
        holdQuestions.put(8018,new Questions("What is the smallest particle of any substance called?", "Atom","Quantum","Molecule","Atom",chapter8));
        holdQuestions.put(8019,new Questions("What is anything that takes up space and has mass?", "Matter","Atoms","Particles","Matter",chapter8));
        holdQuestions.put(8020,new Questions("Quantum numbers give the orbital shape, the size and the orientation and _____ value of an electron.", "swirl","electrical","spin","spin",chapter8));
        holdQuestions.put(8021,new Questions("How many quantum numbers are needed to describe the energy state of an electron in an atom?", "4","6","3","4",chapter8));
        holdQuestions.put(8022,new Questions("The four quantum numbers are the principle, angular momentum, electron spin, and  _________ quantum number.", "magnetic","electrical","primary","magnetic",chapter8));
        holdQuestions.put(8023,new Questions("How many orbitals are in the p subshell?", "1","5","3","3",chapter8));
        holdQuestions.put(8024,new Questions("An atomic orbital is a mathematical function describing the ________ and wave-like behavior of an electron in an atom.", "location","shape","size","location",chapter8));

        holdQuestions.put(8025,new Questions("How many orbitals are in the s subshell?", "3","1","4","1",chapter8));
        holdQuestions.put(8026,new Questions("How many orbitals are in the d subshell?", "7","3","5","5",chapter8));
        holdQuestions.put(8027,new Questions("The angular momentum quantum number describes the ________ of the subshell and its orbitals.", "energy","size","shape","shape",chapter8));
        holdQuestions.put(8028,new Questions("The electron spin quantum number, has only two possible values, 1/2 and...", "-1/2","1","-1","-1/2",chapter8));
        holdQuestions.put(8029,new Questions("The principle quantum number, describes the energy and distance from the _______, and represents the shell.", "nucleus","orbital","protons","nucleus",chapter8));
        holdQuestions.put(8030,new Questions("The l quantum number describes the shape of the... ", "orbital","atom","electron","orbital",chapter8));
        holdQuestions.put(8031,new Questions("A photon with a higher frequency also has a higher ________.", "peroid","intensity","energy","energy",chapter8));
        holdQuestions.put(8032,new Questions("Who created an equation to treat the electron as a wave?", "Schrodinger","Heisenberg","de Broglie","Schrodinger",chapter8));

        // HARD CHAPTER 8
        holdQuestions.put(8800,new Questions("In the Schrodinger wave equation, ψ represents:", "wave function","","","",hardChap8));
        holdQuestions.put(8801,new Questions("Loss of energy of an electron culminates in emanation of a...", "proton","photon","neutron","photon",hardChap8));
        holdQuestions.put(8802,new Questions("Max Plank's constant has units...", "J s","J","s","J s",hardChap8));
        holdQuestions.put(8803,new Questions("Most engertic electrons are...", "gamma","x-rays","alpha","gamma",hardChap8));
        holdQuestions.put(8804,new Questions("In electron diffraction, the rings behave as...", "waves","particles","rays","waves",hardChap8));
        holdQuestions.put(8805,new Questions("Energy in an electron in an atom is...", "quantized","continuous","radial","quantized",hardChap8));
        holdQuestions.put(8806,new Questions("In the photoelectric effect, electrons are removed from the...", "surface","inner shell","core","surface",hardChap8));

    }

    //Music Quiz Questions
    private void music_quiz() {

        //MUSIC - CHAPTER 1 - NOTES, SCALES AND  TERMS

        holdQuestions.put(1000,new Questions("On a staff, a notehead is represented by which of the following shapes?", "a triangle","an oval","a bar","an oval",chapter1));
        holdQuestions.put(1001,new Questions("What is symbol is used at the beginning of the staff to determine pitch of particular line?", "flag","ledger","grand staff","clef",chapter1));
        holdQuestions.put(1002,new Questions("What does the bass clef set?", "C","G","F","F",chapter1));
        holdQuestions.put(1003,new Questions("The position of a single sound within the complete range of sound is known as:", "timbre","pitch","tone","pitch",chapter1));
        holdQuestions.put(1004,new Questions("Which scale is the only major scale that does not use sharps or flats?", "C major","A major","D major","C major",chapter1));
        holdQuestions.put(1005,new Questions("The smallest musical interval (the distance between two adjacent notes) is known as:", "a semitone","a first","a major third","a semitone",chapter1));
        holdQuestions.put(1006,new Questions("A heptatonic scale consisting of five whole tones and two semitones represents which type of scale?", "chromatic","pentatonic","diatonic","diatonic",chapter1));
        holdQuestions.put(1007,new Questions("A chord consisting of three tones of a diatonic scale is called a:", "treble","seventh","triad","triad",chapter1));
        holdQuestions.put(1008,new Questions("An interval whose higher note has a sound-wave frequency of vibration twice that of its lower note is called:", "an octave","an interval","a pitch","an octave",chapter1));
        holdQuestions.put(1009,new Questions("A note that does not usually occur in the key of a given piece of music is called:", "syncopation","an accidental","harmony","an accidental",chapter1));
        holdQuestions.put(1010,new Questions("What is the first note of any diatonic scale?", "tonic","leading tone","major","tonic",chapter1));

        holdQuestions.put(1011,new Questions("What mode represents the natural diatonic, or major, scale?", "Ionian","Aeolian","Dorian","Ionian",chapter1));
        holdQuestions.put(1012,new Questions("What mode represents the natural minor scale?", "Aeolian","Ionian","Mixolydian","Aeolian",chapter1));
        holdQuestions.put(1013,new Questions("The top number in a time signature represents the number of ________ in a measure.", "beats","clicks","turns","beats",chapter1));
        holdQuestions.put(1014,new Questions("The seven naturally occurring chords in a major or minor scale are known collectively as:", "diatonic chords","intervals","pentatonic chords","diatonic chords",chapter1));
        holdQuestions.put(1015,new Questions("Which of these indicates pitch in musical notation?", "clef","cliff","treble","clef",chapter1));
        holdQuestions.put(1016,new Questions("The word tempo refers to what aspect of a piece of music?", "speed","volume","pitch","speed",chapter1));
        holdQuestions.put(1017,new Questions("Music sung without instrumental accompaniment is called:", "pastoral","impromptu","a cappella","a cappella",chapter1));
        holdQuestions.put(1018,new Questions("In classical music, a woman with a very high singing voice would be called a:", "soprano","bass","baritone","soprano",chapter1));
        holdQuestions.put(1019,new Questions("Which is NOT a clef symbol?", "bass","Y","C","Y",chapter1));


        holdQuestions.put(1020,new Questions("How many beats does a half note receive?", "2","4","8","2",chapter1));
        holdQuestions.put(1021,new Questions("How many beats does a quarter note receive?", "1","3","2","1",chapter1));
        holdQuestions.put(1022,new Questions("How many lines make up a music staff?", "4","5","6","5",chapter1));
        holdQuestions.put(1023,new Questions("What does musical term crescendo mean?", "gradually gets softer","gradually gets slower","gradually gets louder","gradually gets louder",chapter1));
        holdQuestions.put(1024,new Questions("What does rest symbol indicate?", "indicates silence","reduction in tempo","slow and steady","indicates silence",chapter1));

        holdQuestions.put(1025,new Questions("What is the name for a series of notes?", "scale","bar","coda","scale",chapter1));
        holdQuestions.put(1026,new Questions("What is the musical scale that is generally considered to sound sad?", "pentatonic","minor","major","minor",chapter1));
        holdQuestions.put(1027,new Questions("What is another name for the treble clef?", "G clef","F clef","C clef","G clef",chapter1));
        holdQuestions.put(1028,new Questions("Which of the following major scales contains F sharp and C sharp?", "F major","C major","D major","D major",chapter1));
        holdQuestions.put(1029,new Questions("Which of these tempos is fastest?", "vivace","moderato","andante","vivace",chapter1));
        holdQuestions.put(1030,new Questions("What does the C clef determine?", "lower C","higher C","middle C","middle C",chapter1));

        holdQuestions.put(1800,new Questions("A key signature is the arrangement of...", "flats","sharps","flats and sharps","flats and sharps",hardChap1));
        holdQuestions.put(1801,new Questions("What scale has no sharps and flats?", "C major","D major","F major","C major",hardChap1));
        holdQuestions.put(1802,new Questions("The time signature 3/4 means we are counting in groups of ____.", "2","3","4","3",hardChap1));
        holdQuestions.put(1803,new Questions("An interval is a difference in pitch between ______ sounds.", "two","three","four","two",hardChap1));
        holdQuestions.put(1804,new Questions("How many pitches does the chromatic scale contains?", "10","12","8","12",hardChap1));
        holdQuestions.put(1805,new Questions("Which scale is made up of just five different notes?", "Pentatonic","Major","Natural Minor","Pentatonic",hardChap1));
        holdQuestions.put(1806,new Questions("Which scale only moves in half steps?", "Half Step","Chromatic","Major","Chromatic",hardChap1));
        holdQuestions.put(1807,new Questions("The sharp sign _______ a note by a semitone.", "raises","lowers","retains","raises",hardChap1));
        holdQuestions.put(1808,new Questions("What is the 4th tone of the E major scale?", "A","C#","D#","A",hardChap1));
        holdQuestions.put(1809,new Questions("What is the 3rd tone of the F major scale?", "Bb","A","C","Bb",hardChap1));


        //CHAPTER 2 - INSTRUMENTS

        holdQuestions.put(2000,new Questions("Which of these musical instruments is also called the whistle flute?", "fipple flute","piccolo","octave flute","fipple flute",chapter2));
        holdQuestions.put(2001,new Questions("Who invented the clarinet?", "Johann Christoph Denner","George Frideric Handel","Adolphe Sax","Johann Christoph Denner",chapter2));
        holdQuestions.put(2002,new Questions("What is the name of an African musical instrument that resembles a xylophone?", "samisen","balafon","sarangi","balafon",chapter2));
        holdQuestions.put(2003,new Questions("In a Japanese hayashi ensemble, percussion instruments are played in combination with which instrument?", "the suzu","the flute","the harp","the flute",chapter2));
        holdQuestions.put(2004,new Questions("Which percussion instrument symbolizes prosperity and marks social status in China?", "gong","rattle","xylophone","gong",chapter2));
        holdQuestions.put(2005,new Questions("Which is Korea’s national instrument?", "kayagŭm","saeng","kŏmungo","kayagŭm",chapter2));
        holdQuestions.put(2006,new Questions("What is the name of an African musical instrument that resembles a xylophone but is made with animal horns, skins, and wood?", "sarangi","mbira","balafon","balafon",chapter2));
        holdQuestions.put(2007,new Questions("What provides the sound in a carillon?", "bell","reed","pipe","bell",chapter2));

        holdQuestions.put(2008,new Questions("What are two of the oldest instruments?", "drum and flute","drum and piano","harp and flute","drum and flute",chapter2));
        holdQuestions.put(2009,new Questions("Which of these is not a percussion instrument?", "drum","cymbal","bugle","bugle",chapter2));
        holdQuestions.put(2010,new Questions("What is a tabla?", "hand drum","a horn","musical table","hand drum",chapter2));
        holdQuestions.put(2011,new Questions("Which of these is a wind instrument?", "pipe organ","zither","double bass","pipe organ",chapter2));
        holdQuestions.put(2012,new Questions("Which instrument is most closely related to the accordion?", "harmonica","organ","trumpet","harmonica",chapter2));
        holdQuestions.put(2013,new Questions("Where did the banjo originate?", "Russia","India","Africa","Africa",chapter2));
        holdQuestions.put(2014,new Questions("Which of the following is NOT a reed instrument?", "trombone","clarinet","saxophone","trombone",chapter2));
        holdQuestions.put(2015,new Questions("How many strings does a cello have?", "8","4","5","4",chapter2));

        holdQuestions.put(2016,new Questions("What material was originally used to make violin strings?", "animal intestines","tree roots","earthworms","animal intestines",chapter2));
        holdQuestions.put(2017,new Questions("What is the literal translation of ukulele?", "little guitar","jumping flea","dull ax","jumping flea",chapter2));
        holdQuestions.put(2018,new Questions("What is another word for panpipes? is another word for panpipes?", "cirrus","syringe","syrinx","syrinx",chapter2));
        holdQuestions.put(2019,new Questions("Which of these is not a percussion instrument?", "tom-tom","snare drum","flute","flute",chapter2));
        holdQuestions.put(2020,new Questions("How many strings does a violin have?", "4","6","7","4",chapter2));
        holdQuestions.put(2021,new Questions("Which of the following is not a keyboard instrument?", "piano","pipe organ","xylophone","xylophone",chapter2));
        holdQuestions.put(2022,new Questions("What is the higher-pitched version of a flute called?", "concertina","piccolo","kazoo","piccolo",chapter2));
        holdQuestions.put(2023,new Questions("Which of these is not a type of African drum?", "djembe","talking drum","timpani","timpani",chapter2));

        holdQuestions.put(2024,new Questions("How many pedals does a grand piano have?", "2","3","5","3",chapter2));
        holdQuestions.put(2025,new Questions("What the saxophone first used for?", "military music","wall ornament","bluegrass music","military music",chapter2));
        holdQuestions.put(2026,new Questions("What is the name for the end of a horn that projects the sound?", "bell","barrel","gong","bell",chapter2));
        holdQuestions.put(2027,new Questions("What is the word for a kind of stringed instrument?", "harp","harmonica","harpy","harp",chapter2));
        holdQuestions.put(2028,new Questions("What family of instruments does the tuba belong to?", "Brass","Percussion","Strings","Brass",chapter2));
        holdQuestions.put(2029,new Questions("What is the tuba made out of?", "Brass","Leather","Tin","Brass",chapter2));
        holdQuestions.put(2030,new Questions("Koto is a", "string instrument","wind instrument","percussion instrument","string instrument",chapter2));
        holdQuestions.put(2031,new Questions("Which of these instruments was created in India?", "sitar","biwa","pipa","sitar",chapter2));

        holdQuestions.put(2800,new Questions("What class of musical instruments does the theremin belong to?", "electronic","wind","percussion","electronic",hardChap2));
        holdQuestions.put(2801,new Questions("Which instrument belongs to the Percussion family?", "Timpani","Atenteben","Euphonium","Timpani",hardChap2));
        holdQuestions.put(2802,new Questions("Which clef does the clarinet use?", "Bass clef","Tenor clef","Treble clef","Treble clef",hardChap2));
        holdQuestions.put(2803,new Questions("Which instrument would una corde apply to?", "violin","piano","guitar","piano",hardChap2));
        holdQuestions.put(2804,new Questions("Which clef does the violin use?", "Treble","Alto","Tenor","Treble",hardChap2));
        holdQuestions.put(2805,new Questions("Which instrument uses the tenor clef?", "Piano","Oboe","Cello","Cello",hardChap2));
        holdQuestions.put(2806,new Questions("Which instrument belongs to the same family as the cor anglais?", "Bassoon","Violin","Cymbals","Bassoon",hardChap2));
        holdQuestions.put(2807,new Questions("Which instrument plays double stops?", "violin","trumpet","oboe","violin",hardChap2));
        holdQuestions.put(2808,new Questions("Which family of instruments does the euphonium belong to?", "Brass","Wind","Percussion","Brass",hardChap2));
        holdQuestions.put(2809,new Questions("The Marimba belongs to the ______ family.", "percussion","woodwinds","brass","percussion",hardChap2));



        //CHAPTER 3 - COMPOSITIONS and COMPOSERS

        holdQuestions.put(3000,new Questions("Which of these composers wrote atonal music?", "Monteverdi","Tallis","Schoenberg","Schoenberg",chapter3));
        holdQuestions.put(3001,new Questions("Which of these composers was not alive in the 20th century?", "Wagner","Puccini","Prokofiev","Wagner",chapter3));
        holdQuestions.put(3002,new Questions("Which of thee composers was alive in the 20th century?", "Rachmaninoff","Vivaldi","chopin","Rachmaninoff",chapter3));
        holdQuestions.put(3003,new Questions("Which of these is NOT by Pyotr Tchaikovsky?", "Für Elise","Swan Lake","Romeo and Juliet","Für Elise",chapter3));
        holdQuestions.put(3004,new Questions("Who composed Sabre Dance?", "Pyotr Tchaikovsky","Aram Khachaturian","Maurice Ravel","Aram Khachaturian",chapter3));
        holdQuestions.put(3005,new Questions("Which country was the composer Claude Debussy born?", "France","Switzerland","Germany","France",chapter3));
        holdQuestions.put(3006,new Questions("What composer wrote symphonies and other major works before he was 13 years old?", "Mozart","Liszt","Beethoven","Mozart",chapter3));
        holdQuestions.put(3007,new Questions("Who composed Moonlight Sonata?", "Ludwig van Beethoven","Johannes Brahms","Richard Wagner","Ludwig van Beethoven",chapter3));

        holdQuestions.put(3008,new Questions("Who composed the Brandenburg Concertos?", "Johann Sebastian Bach","Frédéric Chopin","Claude Debussy","Johann Sebastian Bach",chapter3));
        holdQuestions.put(3009,new Questions("Who composed The Marriage of Figaro?", "Wolfgang Amadeus Mozart","Joseph Haydn","Claude Debussy","Wolfgang Amadeus Mozart",chapter3));
        holdQuestions.put(3010,new Questions("Who composed The Flying Dutchman and Tristan and Isolde?", "Joseph Haydn","Antonio Vivaldi","Richard Wagner","Richard Wagner",chapter3));
        holdQuestions.put(3011,new Questions("Who composed Prelude to the Afternoon of a Faun?", "Wolfgang Amadeus Mozart","Johannes Brahms","Claude Debussy","Claude Debussy",chapter3));
        holdQuestions.put(3012,new Questions("Who composed the ballets Swan Lake, Sleeping Beauty, and The Nutcracker?", "Antonio Vivaldi","Pyotr Ilyich Tchaikovsky","Johannes Brahms","Pyotr Ilyich Tchaikovsky",chapter3));
        holdQuestions.put(3013,new Questions("Who composed Heroic Polonaise?", "Pyotr Ilyich Tchaikovsky","Claude Debussy","Frédéric Chopin","Frédéric Chopin",chapter3));
        holdQuestions.put(3014,new Questions("Who composed Emperor Quartet?", "Johann Sebastian Bach","Richard Wagner","Joseph Haydn","Joseph Haydn",chapter3));
        holdQuestions.put(3015,new Questions("Who composed The Four Seasons?", "Antonio Vivaldi","Johannes Brahms","Claude Debussy","Antonio Vivaldi",chapter3));

        holdQuestions.put(3016,new Questions("Which of these compositions is not by Czech composer Antonín Dvorák?", "Slavonic Dances","Trout Quintet","Moravian Duets","Trout Quintet",chapter3));
        holdQuestions.put(3017,new Questions("Which of these compositions is not by George Frideric Handel?", "Water Music","Messiah","The Blue Danube","The Blue Danube",chapter3));
        holdQuestions.put(3018,new Questions("Who composed Boris Godunov and Pictures at an Exhibition?", "Thomas Tallis","Modest Musorgsky","Antonio Vivaldi","Modest Musorgsky",chapter3));
        holdQuestions.put(3019,new Questions("Who wrote Rondo alla turca?", "Johann Sebastian Bach","Jacques Offenbach","Wolfgang Amadeus Mozart","Wolfgang Amadeus Mozart",chapter3));
        holdQuestions.put(3020,new Questions("Who wrote Tales from the Vienna Woods?", "Richard Strauss","Johann Strauss","Levi Strauss","Johann Strauss",chapter3));
        holdQuestions.put(3021,new Questions("Who composed the Messiah, first performed in 1742?", "Richard Wagner","Georg Hegel","George Frideric Handel","George Frideric Handel",chapter3));
        holdQuestions.put(3022,new Questions("Thomas Tallis was a composer of:", "minimalist music","melodic jazz","church music","church music",chapter3));
        holdQuestions.put(3023,new Questions("Which of these is by Johann Strauss?", "The Blue Danaube","Messiah","Sleeping Beauty","The Blue Danaube",chapter3));

        holdQuestions.put(3024,new Questions("Which contemporary composer wrote a piece consisting entirely of silence?", "John Cage","György Ligeti","Karlheinz Stockhausen","John Cage",chapter3));
        holdQuestions.put(3025,new Questions("Which renowned Russian Romantic composer was also a professor of chemistry?", "Aleksandr Borodin","Mikhail Glinka","Modest Mussorgsky","Aleksandr Borodin",chapter3));
        holdQuestions.put(3026,new Questions("Which major American composer also ran a successful insurance company?", "Aaron Copland","Samuel Barber","Charles Ives","Charles Ives",chapter3));
        holdQuestions.put(3027,new Questions("Sergei Rachmaninoff was a Russian born composer, what instrument was he talented in?", "piano","violin","trombone","piano",chapter3));
        holdQuestions.put(3028,new Questions("Which sense did Ludwig Van Beethoven eventually lose completely?", "touch","sight","hearing","hearing",chapter3));
        holdQuestions.put(3029,new Questions("Antonio Vivaldi was a priest at one point in his life. What was his nickname?", "Red Priest","Captain","The Prodigy","Red Priest",chapter3));
        holdQuestions.put(3030,new Questions("Mozart wrote his first sonata at the age of ", "2","4","16","4",chapter3));
        holdQuestions.put(3031,new Questions("Bach, Handel and Vivaldi are from what musical era?", "Baroque Era","Classical Era","Romantic Era","Baroque Era",chapter3));

        holdQuestions.put(3800,new Questions("What did Pyotr Tchaikovsky study before he studied musical composition?", "chemistry","law","dentistry","law",hardChap3));
        holdQuestions.put(3801,new Questions("Which composer is considered a pioneer of impressionism?", "Claude Debussy","Virgil Thompson","Domenico Scarlatti","Claude Debussy",hardChap3));
        holdQuestions.put(3802,new Questions("Who wrote the music for Austria’s national anthem?", "Wolfgang Amadeus Mozart","Franz Liszt","J.S. Bach","Wolfgang Amadeus Mozart",hardChap3));
        holdQuestions.put(3803,new Questions("Who composed the Trout quintet?", "Schubert","Shostakovich","Satie","Schubert",hardChap3));
        holdQuestions.put(3804,new Questions("Who composed The Well-Tempered Clavier?", "J.S. Bach","Nina Simone","Brian Eno","J.S. Bach",hardChap3));
        holdQuestions.put(3805,new Questions("Which of Beethoven's symphonies does Ode to Joy come from?", "Ninth","Fifth","Second","Ninth",hardChap3));
        holdQuestions.put(3806,new Questions("Who wrote the Requiem in D minor?", "Mozart","Hayden","Beethoven","Mozart",hardChap3));
        holdQuestions.put(3807,new Questions("Who wrote the Sunrise String Quartet?", "Haydn","Mozart","Bach","Haydn",hardChap3));
        holdQuestions.put(3808,new Questions("How many symphonies did Beethoven write?", "13","9","17","9",hardChap3));
        holdQuestions.put(3809,new Questions("Who was known as the \"Father of the String Quartet\"?", "Haydn","Shostakovich","Beethoven","Haydn",hardChap3));


        //CHAPTER 4 - MUSICAL TERMS

        holdQuestions.put(4000,new Questions("What is the meaning of Staccato?", "long","flowing","short","short",chapter4));
        holdQuestions.put(4001,new Questions("What is the meaning of fine?", "the end","the middle","the beginning","the end",chapter4));
        holdQuestions.put(4002,new Questions("Forte means", "loud","very loud","soft","loud",chapter4));

        holdQuestions.put(4003,new Questions("The meaning of Mezzo forte is", "moderately loud","moderately quiet","moderately quick","moderately loud",chapter4));
        holdQuestions.put(4004,new Questions("Piano is", "quiet","soft","very quiet","quiet",chapter4));
        holdQuestions.put(4005,new Questions("Pianissimo is", "very quick","very quiet","loud","very quiet",chapter4));

        holdQuestions.put(4006,new Questions("Diminuendo means", "gradually getting softer","gradually getting quicker","gradually getting quieter","gradually getting quieter",chapter4));
        holdQuestions.put(4007,new Questions("Accelerando means", "gradually getting softer","gradually getting faster","gradually getting quieter","gradually getting faster",chapter4));
        holdQuestions.put(4008,new Questions("The meaning of moderato is", "medium tempo","slow tempo ","quick","medium tempo",chapter4));
        holdQuestions.put(4009,new Questions("What is allegro in music?", "brisk lively tempo","walking pace","medium tempo","brisk lively tempo",chapter4));
        holdQuestions.put(4010,new Questions("Andante is ", "moderately slow","moderately fast","very slow","moderately slow",chapter4));
        holdQuestions.put(4011,new Questions("What is adagio in music?", "slow tempo ","walking pace","moderately fast","slow tempo ",chapter4));
        holdQuestions.put(4012,new Questions("What term means to repeat from beginning?", "da capo","fine","dal segno","da capo",chapter4));
        holdQuestions.put(4013,new Questions("Legato means", "stiffly","smoothly","crisply","smoothly",chapter4));

        holdQuestions.put(4014,new Questions("Which of these terms means very quiet?", "pp","p","f","pp",chapter4));
        holdQuestions.put(4015,new Questions("Which of these of terms means in a singing style?", "cantabile","andante","crescendo","cantabile",chapter4));
        holdQuestions.put(4016,new Questions("Which of these terms would use to play a piece loudly?", "f","p","pp","f",chapter4));
        holdQuestions.put(4017,new Questions("Preto means", "very fast","lively and fast","","very fast",chapter4));
        holdQuestions.put(4018,new Questions("Largo means", "very slow and solemn","very fast","slightly less slow","very slow and solemn",chapter4));
        holdQuestions.put(4019,new Questions("Grave means", "slow and serious","quiet","mysterious","slow and serious",chapter4));

        holdQuestions.put(4020,new Questions("Affettuoso means", "pleasant","singing","tenderly","tenderly",chapter4));
        holdQuestions.put(4021,new Questions("Mezzo means", "half","whole","full","half",chapter4));
        holdQuestions.put(4022,new Questions("The musical term for detached is", "staccato","legato","andante","staccato",chapter4));

        holdQuestions.put(4023,new Questions("What is the meaning of dolce?", "sweetly","sorrowful","energetic","sweetly",chapter4));

        holdQuestions.put(4800,new Questions("What does 8va mean?", "Play an octave lower","silent","Play an octave higher","Play an octave higher",hardChap4));
        holdQuestions.put(4801,new Questions("Mezzo piano means", "slightly soft","soft","gradually softer","slightly soft",hardChap4));
        holdQuestions.put(4802,new Questions("Lesto means", "freely","lively","quickly","lively",hardChap4));
        holdQuestions.put(4803,new Questions("Giojoso means", "joyful","gracefully","grandly","joyful",hardChap4));
        holdQuestions.put(4804,new Questions("What does tristamente mean?", "sorrowful","calm","lively","sorrowful",hardChap4));
        holdQuestions.put(4805,new Questions("Fermata means", "loudly","stop","prolonged note","prolonged note",hardChap4));
        holdQuestions.put(4806,new Questions("Fortissimo means", "loud","moderately loud","very loud","very loud",hardChap4));
        holdQuestions.put(4807,new Questions("Sforzando means", "sudden, forced loud","quiet","slow","sudden, forced loud",hardChap4));


        //CHAPTER 5 - BAROQUE PEROID

        holdQuestions.put(5000,new Questions("The term Baroque represents all the following words expect", "flamboyant","naturalistic","bizarre","naturalistic",chapter5));
        holdQuestions.put(5001,new Questions("What does the term baroque represent in music?", "a musical style","decline in music","types of instruments","a musical style",chapter5));
        holdQuestions.put(5002,new Questions("The baroque period lasted between", "1600 - 1750","1700 - 1850","1725 - 1800","1600 - 1750",chapter5));
        holdQuestions.put(5003,new Questions("The two most popular baroque composers were Johann Sebastian Bach and", "George Frideric Handel","Mozart","Chopin","George Frideric Handel",chapter5));
        holdQuestions.put(5004,new Questions("All of the following were major baroque composers except", "Wolfgang A. Mozart","Antonio Vivaldi","Arcangelo Corelli","Wolfgang A. Mozart",chapter5));
        holdQuestions.put(5005,new Questions("The main keyboard instruments of the baroque period were the organ and the", "piano","accordion","clavichord","piano",chapter5));
        holdQuestions.put(5006,new Questions("Which of the following is not a part of the baroque suite?", "lutenist","harpsichordist","violinist","violinist",chapter5));
        holdQuestions.put(5007,new Questions("Baroque composer Vivaldi was famous and influential as a virtuoso", "waltz","allemande","sarabande","waltz",chapter5));

        holdQuestions.put(5008,new Questions("Composers in the middle baroque phase favored writing compositions for instruments of the __________ family.", "brass","percussion","violin","violin",chapter5));
        holdQuestions.put(5009,new Questions("How many movements does a Baroque concerto usually have?", "3","4","5","3",chapter5));
        holdQuestions.put(5010,new Questions("Which Baroque form might feature an overture?", "Concerto grosso","orchestral suite","Solo concerto","orchestral suite",chapter5));
        holdQuestions.put(5011,new Questions("Which Baroque form uses the concertino and the ripieno?", "concerto grosso","cantata","oratorio","concerto grosso",chapter5));
        holdQuestions.put(5012,new Questions("Which describes the texture of most Baroque music?", "polyphonic","monophonic","homophonic","polyphonic",chapter5));
        holdQuestions.put(5013,new Questions("Which woodwind instruments might be found in a Baroque orchestra?", "oboes","horns","cellos","oboes",chapter5));
        holdQuestions.put(5014,new Questions("The term 'Baroque' has roots from which Portuguese word?", "barroco","barocorra","borocue","barroco",chapter5));
        holdQuestions.put(5015,new Questions("Which of the two instruments could play basso continuo?", "Saxophone and cello","Bassoon and harpsichord","Oboe and Piano","Bassoon and Harpsichord",chapter5));

        holdQuestions.put(5016,new Questions("The Italian composer, Vivaldi, wrote concertos", "string instruments","keyboard instruments","variety of instruments","variety of instruments",chapter5));
        holdQuestions.put(5017,new Questions("Which of the following forms originated in the Baroque period?", "Fugue","Oratorio","All of the above","All of the above",chapter5));
        holdQuestions.put(5018,new Questions("Movements of a baroque suite can differ all of the following except", "keys","tempo","meter","keys",chapter5));
        holdQuestions.put(5019,new Questions("Handel's Messiah is an example of", "an oratorio","an opera","a song","an oratorio",chapter5));
        holdQuestions.put(5020,new Questions("Which of the following is NOT a baroque keyboard instrument?", "Taille","Clavichord","Harpsichord","Taille",chapter5));
        holdQuestions.put(5021,new Questions("How much instruments does basso continuo consist of?", "2","4","6","2",chapter5));
        holdQuestions.put(5022,new Questions("How many performers does a trio sonata consist of?", "4","3","2","4",chapter5));


        holdQuestions.put(5023,new Questions("What is the texture of Baroque music?", "Polyphonic","Monophonic","Homophonic","Polyphonic",chapter5));
        holdQuestions.put(5024,new Questions("Which of these composers was from the Baroque music era?", "Jean-Philippe Rameau","Thomas Tallis","William Byrd","Jean-Philippe Rameau",chapter5));
        holdQuestions.put(5025,new Questions("The pieces of music that focus on this perspective were significant to this musical period:", "social","political","religous","religous",chapter5));
        holdQuestions.put(5026,new Questions("What was an important style of instrumental music in this era?", "concerto","sonata","virtuoso","concerto",chapter5));


        holdQuestions.put(5800,new Questions("What style of music was not introduced in the Baroque era?", "symphony","opera","sonata","symphony",hardChap5));
        holdQuestions.put(5801,new Questions("Which Baroque composer set each piece of music to four seasons?", "Antonin Vivaldi","Arcangello Corelli","Johann Sebastian Bach","Antonin Vivaldi",hardChap5));
        holdQuestions.put(5802,new Questions("Which of these pieces of music was not composed in the Baroque music era?", "Ave Maria","Zadok the Priest","The Four Seasons","Ave Maria",hardChap5));
        holdQuestions.put(5803,new Questions("During the Baroque musical era, which of the following composer did not compose any operas?", "Johann Sebastian Bach","Antonio Vivaldi","Alessandro Scarlatti","Johann Sebastian Bach",hardChap5));
        holdQuestions.put(5804,new Questions("A --------------- is a polyphonic composition based on one main theme called a subject.", "Fugue","Recitative","Ritornello","Fugue",hardChap5));


        //CHAPTER 6 - JAZZ

        holdQuestions.put(6000,new Questions("Which American city did jazz originate?" , "Detroit","New Orleans","New York City","New Orleans",chapter6));
        holdQuestions.put(6001,new Questions("What city was home to the famous jazz club 'The Cotton Club'? ", "New York City","New Orleans","Chicago","New York City",chapter6));
        holdQuestions.put(6002,new Questions("The term jazz was used interchangeably with", "blues","ragtime","swinging","ragtime",chapter6));
        holdQuestions.put(6003,new Questions("Which band performed on the first jazz recording?", "The Wolverines","Red Hot Peppers","Original Dixieland Jazz Band ","Original Dixieland Jazz Band ",chapter6));
        holdQuestions.put(6004,new Questions("What does the jazz term cat mean?", "a jazz musician","a cat","category","a jazz musician",chapter6));
        holdQuestions.put(6005,new Questions("What does the jazz term blow mean?", "playing an instrument","terrible performers","improvising","playing an instrument",chapter6));
        holdQuestions.put(6006,new Questions("Jazz originated in what time period?", "1900s","1800s","1700s","1900s",chapter6));
        holdQuestions.put(6007,new Questions("Where did jazz music begin?", "United States","Africa","Europe","United States",chapter6));

        holdQuestions.put(6008,new Questions("Which of the following words were jazz related terms?", "cool and hip","sweet and savory","hot and sweet","cool and hip",chapter6));
        holdQuestions.put(6009,new Questions("Some of the types of jazz include:", "ragtime and blues","cadenza and aria","folk and soul","ragtime and blues",chapter6));
        holdQuestions.put(6010,new Questions("Jazz music was syncopated which means it was _______.", "off beat","on beat","improvised","off beat",chapter6));
        holdQuestions.put(6011,new Questions("Which of the following made jazz distinct from other genres?", "harmony","syncopation","dynamics","harmony",chapter6));
        holdQuestions.put(6012,new Questions("Jazz musicians helped invent what instrument?", "saxophone","trombone","drum set","drum set",chapter6));
        holdQuestions.put(6013,new Questions("Jazz music encompassed the nature of what other historical music?", "spirituals","classical","romantic","spirituals",chapter6));
        holdQuestions.put(6014,new Questions("Jazz helped breech what barrier?", "racial","age","state","racial",chapter6));
        holdQuestions.put(6015,new Questions("Which jazz musician is often called the 'Father of Ragtime?'", "Scott Joplin","John Coltrane","Dizzy Gillespie","Scott Joplin",chapter6));

        holdQuestions.put(6016,new Questions("Which musical style of jazz originated in New Orleans?", "Cool Jazz","Bebop","Dixieland","Dixieland",chapter6));
        holdQuestions.put(6017,new Questions("Which country is the birthplace of Jazz? ", "U.S.A","Germany","France","U.S.A",chapter6));

        holdQuestions.put(6800,new Questions("Which saxophonist in the Dave Brubeck Quartet Group composed Take Five?", "Paul Desmond","Stan Getz","Lester Young","Paul Desmond",hardChap6));
        holdQuestions.put(6801,new Questions("Who composed So What?", "Miles Davis","John Coltrane","Charlie Parker","Miles Davis",hardChap6));
        holdQuestions.put(6802,new Questions("The jazz song Take the A Train was written by...", "Billy Strayhorn","Duke Ellington","Miles Davis","Billy Strayhorn",hardChap6));
        holdQuestions.put(6803,new Questions("Who composed Round Midnight in 1944?", "Dizzy Gillespie","Thelonious Monk","Count Basie","Thelonious Monk",hardChap6));
        holdQuestions.put(6804,new Questions("Who composed Strange Fruit?", "Dizzy Gillespie","Billie Holiday","Abel Meeropol","Abel Meeropol",hardChap6));
        holdQuestions.put(6805,new Questions("Who wrote the jazz song A Night in Tunisia?", "Thelonious Monk","Dizzy Gillespie","John Coltrane","Dizzy Gillespie",hardChap6));


        //CHAPTER 7 - RANDOM

        holdQuestions.put(7000,new Questions("In an Orchestra, which is the largest brass section instrument?", "Trumpet","Tenor","Tuba","Tuba",chapter7));
        holdQuestions.put(7001,new Questions("Name the orchestral instrument that can play high note?", "Piccolo","Cellos","Violin","Violin",chapter7));
        holdQuestions.put(7002,new Questions("The number of strings on a standard Ukulele is?", "4","2","1","4",chapter7));
        holdQuestions.put(7003,new Questions("What age did Janis Joplin, Jimi Hendrix and Kurt Cobain die?", "27","31","24","27",chapter7));
        holdQuestions.put(7004,new Questions("Name the note an Orchestra tunes to?", "C","A","B","A",chapter7));
        holdQuestions.put(7005,new Questions("A “libretto” is:", "A guitar","A short song","opera’s text","opera’s text",chapter7));
        holdQuestions.put(7006,new Questions("What does the C clef determine?", "middle C","high C","lower C","middle C",chapter7));
        holdQuestions.put(7007,new Questions("What is another name for the treble clef?", "G","C","F","G",chapter7));

        holdQuestions.put(7008,new Questions("What does the bass clef set?", "F","G","E","F",chapter7));
        holdQuestions.put(7009,new Questions("How many musicians would the performance of a nonet require?", "9","12","15","9",chapter7));
        holdQuestions.put(7010,new Questions("In music, a caesura is:", "A pause","A short note","A young singer","A pause",chapter7));
        holdQuestions.put(7011,new Questions("The madrigal is a type of chamber music written for what instrument?", "Organ","Harp","Voice","Voice",chapter7));
        holdQuestions.put(7012,new Questions("In what key is a concert flute pitched?", "C","A","E","C",chapter7));
        holdQuestions.put(7013,new Questions("The piano piece known as \"The Minute Waltz\" was written by whom?", "Franz Liszt","Sergei Rachmaninoff","Frederic Chopin","Frederic Chopin",chapter7));
        holdQuestions.put(7014,new Questions("Who wrote the Liebestraum pieces?", "Franz Liszt","Erik Satie ","Frederic Chopin","Franz Liszt",chapter7));
        holdQuestions.put(7015,new Questions("The musical work, The Sorcerer's Apprentice, was written by whom?", "Paul Dukas","Camille Saint-Saens","Maurice Ravel","Paul Dukas",chapter7));

        holdQuestions.put(7016,new Questions("Which term means \"one single line of music\"?", "Monophonic","Stereophonic","Homophonic","Monophonic",chapter7));
        holdQuestions.put(7017,new Questions("The period of music which followed Medieval is the:", "Renaissance","Classical","Romantic","Renaissance",chapter7));
        holdQuestions.put(7018,new Questions("Which instrument was invented during the Renaissance period?", "Violoncello","Viol","Violin","Viol",chapter7));
        holdQuestions.put(7019,new Questions("The Baroque era began in about 1600 and ended at the time of the death of which composer?", "Bach","Beethoven","Mozart","Bach",chapter7));
        holdQuestions.put(7020,new Questions("The Classical music period spans from the death of which composer to the death of which other composers?", "Bach to Beethoven","Mozart to Beethoven","Bach to Mozart","Bach to Beethoven",chapter7));
        holdQuestions.put(7021,new Questions("Which instrument became hugely popular in the Classical era?", "piano","Viola","flute","piano",chapter7));
        holdQuestions.put(7022,new Questions("In the Romantic era, the focus was on:", "form","style","emotion","emotion",chapter7));
        holdQuestions.put(7023,new Questions("Which element of music means high or low?", "Pitch","Dynamics","Texture","Pitch",chapter7));

        holdQuestions.put(7024,new Questions("Which element describes how loud or quiet music is?", "Dynamics","Tonality","Tempo","Dynamics",chapter7));
        holdQuestions.put(7025,new Questions("Which element describes the order and arrangements of the parts of the music?", "Harmony","Form","Dynamics","Form",chapter7));
        holdQuestions.put(7026,new Questions("Which instrument family is the trombone in?", "Brass","Percussion","Woodwind","Brass",chapter7));
        holdQuestions.put(7027,new Questions("Which instrument is part of the woodwind family?", "oboe","tambourine","cornet","oboe",chapter7));
        holdQuestions.put(7028,new Questions("pp is an abbreviation for which of the following Italian terms?", "piano","pianississimo","pianissimo","pianissimo",chapter7));
        holdQuestions.put(7029,new Questions("ppp is the abbreviation for which of the following Italian terms?", "pianissimo","pianississimo","pianissississimo","pianississimo",chapter7));
        holdQuestions.put(7030,new Questions("mf symbol means", "moderately loud","medium soft","loud","moderately loud",chapter7));
        holdQuestions.put(7031,new Questions("The dynamic effect of gradually growing louder is called", "Crescendo","Decrescendo","Accelerando","Crescendo",chapter7));

        holdQuestions.put(7800,new Questions("Which U.S.A city is considered the capital of country music?", "Nashville","Memphis","Houston","Nashville",hardChap7));
        holdQuestions.put(7801,new Questions("Which came first the Romantic, Baroque or Renaissance period of music?", "Baroque","Romantic","Renaissance","Renaissance",hardChap7));
        holdQuestions.put(7802,new Questions("What are syncopations in jazz?", "an instrument","offbeat music","music halls","offbeat music",hardChap7));
        holdQuestions.put(7803,new Questions("Which instrument is NOT commonly used in Celtic music?", "fiddle","bagpipes","zither","zither",hardChap7));
        holdQuestions.put(7804,new Questions("What is the name of a slow mournful Irish song?", "airs","hymns","minuet","airs",hardChap7));
        holdQuestions.put(7805,new Questions("The smaller the vibrating element the ________ its pitch.", "higher","lower","softer","higher",hardChap7));
        holdQuestions.put(7806,new Questions("Timbre is synonymous with...", "sound","tone color","vibrations","tone color",hardChap7));
        holdQuestions.put(7807,new Questions("Which of the followng is NOT a brass instrument?", "cornet","french horn","clarinet","clarinet",hardChap7));
        holdQuestions.put(7808,new Questions("Two part form is also known as...", "Binary","Ternary","Rondo","Binary",hardChap7));
        holdQuestions.put(7809,new Questions("Three part form is also known as...", "Ternary","Rondo","Variation Form","Ternary",hardChap7));


        //CHAPTER 8 - Guess the Singer/Band - 80s -
        holdQuestions.put(8000,new Questions("Which band sung the hit single Take On Me in 1985?", "The Cars","a-ha","Pet Shop Boys","a-ha",chapter8));
        holdQuestions.put(8001,new Questions("Which rock band sung Pur Some Sugar On Me?", "Guns and Roses","Def Leppard","Van Halen","Def Leppard",chapter8));
        holdQuestions.put(8002,new Questions("Karma Chameleon was sung by...", "Culture Club","Men Without Hats","A Flock of Seagulls","Culture Club",chapter8));
        holdQuestions.put(8003,new Questions("Which singer sang Purple Rain?", "Michael Jackson","Prince","Whitney Houston","Prince",chapter8));
        holdQuestions.put(8004,new Questions("Which band sang West End Girls?", "Pet Shop Boys","The Pixies","Devo","Pet Shop Boys",chapter8));
        holdQuestions.put(8005,new Questions("Who sung Where Is My Mind?", "Pet Shop Boys","Beastie Boys","The Pixies","The Pixies",chapter8));
        holdQuestions.put(8006,new Questions("Who sung Living on a Prayer?", "Guns and Roses","Bon Jovi","Prince","Bon Jovi",chapter8));
        holdQuestions.put(8007,new Questions("Who sung Time After Time in 1983?", "Cyndi Lauper","Bon Jovi","Whitney Houston","Cyndi Lauper",chapter8));

        holdQuestions.put(8008,new Questions("Who sung Beat It in 1982?", "Prince","Micheal Jackson","Rockwell","Michael Jackson",chapter8));
        holdQuestions.put(8009,new Questions("Who sung Another One Bites The Dust in 1980?", "The Cranberries","The Human League","Queen","Queen",chapter8));
        holdQuestions.put(8010,new Questions("Who sung Love Shack?", "","","The Bangles","The B-52's",chapter8));
        holdQuestions.put(8011,new Questions("Walk Like An Egyptian was sung by... ", "The Bangles","The Cranberries","The B-52's","The Bangles",chapter8));
        holdQuestions.put(8012,new Questions("Don't You Want Me was sung by...", "Toto","The Human League","A Flock of Seagulls","The Human League",chapter8));
        holdQuestions.put(8013,new Questions("Who sang Africa in 1982?", "Toto","Journey","Guns and Roses","Toto",chapter8));
        holdQuestions.put(8014,new Questions("Eye of The Tiger was sung by...", "Survivor","Guns and Roses","Whitesnake","Survivor",chapter8));
        holdQuestions.put(8015,new Questions("Which band sang Here I Go Again?", "Toto","Survivor","Journey","Whitesnake",chapter8));

        holdQuestions.put(8016,new Questions("Walk This Way in 1986 was sung by...  ?", "Run-D.M.C","Beastie Boys","Public Enemy","Run-D.M.C",chapter8));
        holdQuestions.put(8027,new Questions("You Are the Girl was sung by...", "The Cars","A Flock of Seagulls","a-ha","The Cars",chapter8));
        holdQuestions.put(8018,new Questions("Who sung Since You're Gone in 1981?" , "The Cars","a-ha","Duran Duran","The Cars",chapter8));
        holdQuestions.put(8019,new Questions("Who sung the rap song Paul Revere in 1986?", "Beastie Boys","Salt-N-Pepa","Whodini","Beastie Boys",chapter8));
        holdQuestions.put(8020,new Questions("Which rap group sung Rebel Without A Pause?", "Beastie Boys","Public Enemy","N.W.A.","Public Enemy",chapter8));
        holdQuestions.put(8021,new Questions("Who sung I Ran(So Far Away) in 1982?", "Blondie","Pet Shop Boys","A Flock of Seagulls","A Flock of Seagulls",chapter8));
        holdQuestions.put(8022,new Questions("Who sung Careless Whisper in 1984?", "Michael Bolton","Michael Jackson","George Michael","George Michael",chapter8));
        holdQuestions.put(8023,new Questions("Which band sung Don't Stop Believing?", "Journey","The Cars","Survivor","Journey",chapter8));

    }

    private void history_quiz() {

        //CHAPTER 1 - INDUS VALLEY CIVILISATION

        holdQuestions.put(1000,new Questions("How long did the Indus Valley Civilisation last?", "3300 - 1300 BCE","4300 - 3100 BCE","4500 - 2100 BCE","3300 - 1300 BCE",chapter1));
        holdQuestions.put(1001,new Questions("Which age did the Indus Valley Civilisation belong to?", "Bronze Age","Stone Age","Iron Age","Bronze Age",chapter1));
        holdQuestions.put(1002,new Questions("What are the modern-day countries where the Indus Valley Civilization was located?", "Pakistan and India","Pakistan and Iran","India and Myanmar","Pakistan and India",chapter1));
        holdQuestions.put(1003,new Questions("The Indus Valley was located next to which river?", "Indus River","Kabul River","Neelam River","Indus River",chapter1));
        holdQuestions.put(1004,new Questions("According to new research the largest city of the Indus valley was....", "Mohenjo-daro","Harappa","Rakhigarhi","Rakhigarhi",chapter1));
        holdQuestions.put(1005,new Questions("What material did the Indus Valley people use to make cloth?", "Cotton","Wheat","Barley","Cotton",chapter1));
        holdQuestions.put(1006,new Questions("Jewelery recovered from the Indus Valley was made with what stone?", "Carnelian","Ruby","Sapphire","Carnelian",chapter1));
        holdQuestions.put(1007,new Questions("What was the first Indus Valley city to be discovered?", "Mohenjo-daro","Kalibangan","Harappa","Harappa",chapter1));


        holdQuestions.put(1008,new Questions("What have been found in the Indus Valley with pictures and writing on them?", "scrolls","tablets","seals","seals",chapter1));
        holdQuestions.put(1009,new Questions("Which religion may have some similarities with the Indus Valley religion?", "Hinduism","Christianity","Buddhism","Hinduism",chapter1));
        holdQuestions.put(1010,new Questions("Which of these did the Indus Valley people trade with?", "Romans","Mesopotamians","Persians","Mesopotamians",chapter1));
        holdQuestions.put(1011,new Questions("What is the meaning of the city Mohenjo-daro?", "Mound of the dead","Mound of life","River fortress","Mound of the dead",chapter1));
        holdQuestions.put(1012,new Questions("Which of the following was not a city in the Indus Valley civilisation?", "Tunis","Lothal","Harrapa","Tunis",chapter1));
        holdQuestions.put(1013,new Questions("People of the Indus valley placed important public structures on the citadel because the citadel protected them from enemies and", "flood waters","Disease","nomads","flood waters",chapter1));
        holdQuestions.put(1014,new Questions("What was the economy of the early Indian civilization based on?", "Warfare","Agriculture only","Agriculture and trade","Agriculture and trade",chapter1));
        holdQuestions.put(1015,new Questions("The Great Bath of Indus Valley Civilisation is found at :", "Mohenjo-Daro","Harappa","Kalibangan","Mohenjo-Daro",chapter1));

        holdQuestions.put(1016,new Questions("Along which river was the city of Harappa located next to?", "Sultej River","Indus River","Yangtze River","Ravi River",chapter1));
        holdQuestions.put(1017,new Questions("What was the name of the ancient port of the Indus Civilisation?", "Lothal","Kalibangan","Balakot","Lothal",chapter1));
        holdQuestions.put(1018,new Questions("The \"Dancing Girl\" sculpture found in the Indus Valley was made from...", "Silver","Bronze","Copper","Bronze",chapter1));
        holdQuestions.put(1019,new Questions("How were the Harrappan seals manufactured?", "By cutting","By burning","By molding","By cutting",chapter1));
        holdQuestions.put(1020,new Questions("Which Harrappan Civilisation site has yielded evidence of rice cultivation?", "Lothal","Kalibangan","Mohenjo-Daro","Lothal",chapter1));
        holdQuestions.put(1021,new Questions("Where was the statue of the bearded man found?", "Mohenjo-daro","Lothal","Harappan","Mohenjo-daro",chapter1));
        holdQuestions.put(1022,new Questions("Which musical instrument was NOT recovered from the Indus Valley?", "guitar","cymbals","flute","guitar",chapter1));
        holdQuestions.put(1023,new Questions("Indus Valley seals were NOT made from..", "steatite","terracotta","iron","iron",chapter1));

        holdQuestions.put(1024,new Questions("The port at Lothal was well connected with the river...", "Bhogavo","Tapti","Narmada","Bhogavo",chapter1));
        holdQuestions.put(1025,new Questions("In which Indus Valley Civilization sites, was drainage system absent?", "Banawali","Lothal","Dholavira","Banawali",chapter1));
        holdQuestions.put(1026,new Questions("Which of the following sites is the largest of all Indus settlements excavated?", "Dholavira","Mohenjo-daro","Rakhigarhi","Rakhigarhi",chapter1));
        holdQuestions.put(1027,new Questions("Cylindrical seals of Mesopotamia have been recovered from which two Harappan site?", "Mohenjodaro and Kalibangan","Lothal and Rangpur","Lothal and Kalibangan","Mohenjodaro and Kalibangan",chapter1));
        holdQuestions.put(1028,new Questions("A lot of beads were discovered from:", "Mohenjo-daro","Dholavira","Lothal","Mohenjo-daro",chapter1));
        holdQuestions.put(1029,new Questions("Which metal was unknown to Indus Valley Civilization?", "iron","gold","copper","iron",chapter1));
        holdQuestions.put(1030,new Questions("Which of the following is not correctly matched?", "Kalibangan -> Haryana","Lothal -> Gujarat","Ropar -> Punjab","Kalibangan -> Haryana",chapter1));
        holdQuestions.put(1031,new Questions("Which Harappan site is not found in Gujarat?", "Dhaulvira","Sutkagendor","Sotkakoh","Sutkagendor",chapter1));


        holdQuestions.put(1800,new Questions("Which one of the following Harappan sites is NOT located in Gujarat?", "Surkotada","Sutkagendor","Desalpur","Sutkagendor",hardChap1));
        holdQuestions.put(1801,new Questions("Which of the following Indus Valley sites is presently in Pakistan?", "Kalibangan","Alamgirpur","Harappa","Harappa",hardChap1));
        holdQuestions.put(1802,new Questions("The Social System of the Harappans was...", "slave based","caste based","fairly egalitarian","fairly egalitarian",hardChap1));
        holdQuestions.put(1803,new Questions("There are similarities between the seals found at Mohenjo-Daro and __________.", "Sumeria","China","Egypt","Sumeria",hardChap1));
        holdQuestions.put(1804,new Questions("Which one of the remains from the Indus Valley indicates their commercial and economic development?", "seals","houses","boats","seals",hardChap1));
        holdQuestions.put(1805,new Questions("The most common animal figure found at all the Harappan sites is...", "unihorn bull","cow","bull","unihorn bull",hardChap1));
        holdQuestions.put(1806,new Questions("Which was the only city of the Indus without fortification?", "Harappa","Chanhudaro","Mohenjo-daro","Chanhudaro",hardChap1));
        holdQuestions.put(1807,new Questions("Cereal(s) grown by the people of the Harappan Civilisation was/were...", "millet","rice","both options","both options",hardChap1));
        holdQuestions.put(1808,new Questions("Which was the biggest building in Mohenjo-daro?", "Large Hall","Great Bath","Granary","Granary",hardChap1));
        holdQuestions.put(1809,new Questions("The Harappan town considered to be a town of the artists and craftsmen was...", "Chanhudaro","Mohenjo-Daro","Harappa","Chanhudaro",hardChap1));




        // CHAPTER 2 - ANCIENT MESOPOTAMIA

        holdQuestions.put(2000,new Questions("What is Ancient Mesopotamia sometimes called?", "Cradle of Civilization","The Far East","The New World","Cradle of civilization",chapter2));
        holdQuestions.put(2001,new Questions("In what current country is most of Ancient Mesopotamia located?", "Saudi Arabia","China","Iraq","Iraq",chapter2));
        holdQuestions.put(2002,new Questions("What river or rivers were important to the civilizations of Mesopotamia?", "Yellow and Yangtze River","Tigris and Euphrates River","Nile River","Tigris and Euphrates River",chapter2));
        holdQuestions.put(2003,new Questions("What people were the first to write down and record their system of law?", "Babylonians","Sumerians","Persians","Babylonians",chapter2));
        holdQuestions.put(2004,new Questions("What language was spoken by most of the people throughout the history of Ancient Mesopotamia?", "Greek","Akkadian","Persian","Akkadian",chapter2));
        holdQuestions.put(2005,new Questions("Which civilization in Mesopotamia was known as a warrior society?", "Assyrians","Sumerians","Israelites","Assyrians",chapter2));
        holdQuestions.put(2006,new Questions("What people put an end to the rule of the Assyrians and the Babylonians?", "Egyptians","Persians","Greeks","Persians",chapter2));
        holdQuestions.put(2007,new Questions("The word Mesopotamia is sometimes translated to...?", "fields of blood","the land between rivers","chaotic streams","the land between rivers",chapter2));

        holdQuestions.put(2008,new Questions("What was the first empire of Mesopotamia?", "Assyrian ","Akkadian","Babylonian","Akkadian",chapter2));
        holdQuestions.put(2009,new Questions("Who was the first ruler of the Akkadian Empire?", "Sargon of Akkad","Nebuchadnezzar II","Hammurabi","Sargon of Akkad",chapter2));
        holdQuestions.put(2010,new Questions("How many laws are in the Hammurabi code of laws?", "369","282","410","282",chapter2));
        holdQuestions.put(2011,new Questions("Where did people go to learn reading, writing, religion, law, medicine and even astrology in Mesopotamia?", "temples","schools","churches","schools",chapter2));
        holdQuestions.put(2012,new Questions("The Mesopotamians believed that they were what with the gods?", "co-laborers","subservient","equal to","co-laborers",chapter2));
        holdQuestions.put(2013,new Questions("What was the main system of Mesopotamia?", "Polytheism","Monotheism","Atheism","Polytheism",chapter2));
        holdQuestions.put(2014,new Questions("Which deity was not apart of Mesopotamia?", "Marduk","Nabu","Mafdet","Mafdet",chapter2));
        holdQuestions.put(2015,new Questions("The following people are considered Mesopotamians except:", "Akkadians","Sumerians","Egyptians","Egyptians",chapter2));

        holdQuestions.put(2016,new Questions("What was the writing system of Mesopotamia?", "Stele","cuneiform","heiroglyphs","cuneiform",chapter2));
        holdQuestions.put(2017,new Questions("In literature and in history, who ruled the city of Uruk?", "Gilgamesh","Hammurabi","Nebuchadnazzer II","Gilgamesh",chapter2));
        holdQuestions.put(2018,new Questions("What large structure did the Sumerians develop?", "ziggurat","pyramid","obelisk","ziggurat",chapter2));
        holdQuestions.put(2019,new Questions("The Etemenanki ziggurat means the \"Foundation of _______ and earth\"", "heaven","humans","spirits","heaven",chapter2));
        holdQuestions.put(2020,new Questions("The Etemenanki ziggurat was dedicated to which god?", "Marduk","An","Ninhursag","Marduk",chapter2));
        holdQuestions.put(2021,new Questions("Which is NOT a mesopotamian language?", "Harappan","Hittite","Elamite","Harappan",chapter2));
        holdQuestions.put(2022,new Questions("What was the significance of the ziggurat?", "temples for gods","graves for dead","bath site","temples for gods",chapter2));
        holdQuestions.put(2023,new Questions("Who was Tiglath-Pileser I?", "King of Assyria","Sumerian writer","Artist","King of Assyria",chapter2));

        holdQuestions.put(2024,new Questions("What does the Sumerian term Lugal mean?", "Slave","Ruler","Philosopher","Ruler",chapter2));
        holdQuestions.put(2025,new Questions("Cuneiform writing provided the basis for the development of what?", "recorded history","paintings","traditions","recorded history",chapter2));
        holdQuestions.put(2026,new Questions("In the Hammurabi code, what was the punishment for being caught in the act of committing a robbery?", "return stolen items","hands cut off","death","death",chapter2));
        holdQuestions.put(2027,new Questions("In the Hammurabi code, if a son striked his father, what was his punishment", "hands cut off","exiled","death","hands cut off",chapter2));
        holdQuestions.put(2028,new Questions("Who was the ancient Babylonian king who created the earliest known code of laws which focused on harsh punishments to keep order in society?", "Hammurabi","Eannatum","Sargon","Hammurabi",chapter2));
        holdQuestions.put(2029,new Questions("What river was Babylon located?", "Euphrates River","Tigris River","Nile River","Euphrates River",chapter2));
        holdQuestions.put(2030,new Questions("Who was the main god of the Assyrian people?", "Ashur","Shamash","Ishtar","Ashur",chapter2));
        holdQuestions.put(2031,new Questions("Which Assryian ruler inscripted his exceeding barbaric and savage exploits on his enemies and traitors?", "Ashurnasirpal II","Ashurbanipal","Tukulti-Ninurta II","Ashurnasirpal II",chapter2));

        holdQuestions.put(2800,new Questions("Inanna was the Mesopotamian goddess of...", "wind","love and war","fire","love and war",hardChap2));
        holdQuestions.put(2801,new Questions("In the 2000 BCE, the city that flourished as the royal capital of Mesopotamia was...", "Ur","Mari","Kalibangan","Mari",hardChap2));
        holdQuestions.put(2802,new Questions("The tool used to press signs in the tablets was a...", "reed.","stone.","bronze stamp.","reed.",hardChap2));
        holdQuestions.put(2803,new Questions("Labourers in early mesopotamia were paid in...", "coins.","rations.","silver.","rations.",hardChap2));
        holdQuestions.put(2804,new Questions("Which was a very common staple grown and eaten by the Mesopotamians?", "Barley","Rice","Rye","Barley",hardChap2));

        // CHAPTER 3 - ANCIENT EGYPT

        holdQuestions.put(3000,new Questions("Which ancient Egyptian king established a cult to Aton, a sun god?", "Akhenaten","Tutankhamen","Ramses II","Akhenaten",chapter3));
        holdQuestions.put(3001,new Questions("Which king of Egypt during the 13th century BCE is known for his extensive building programs and the many colossal statues of him?", "Ramses II","Tutankhamen","Sesostris III","Ramses II",chapter3));
        holdQuestions.put(3002,new Questions("What are hieroglyphs?", "boat","temple","picture writing","picture writing",chapter3));
        holdQuestions.put(3003,new Questions("Which of these deities liked to drink blood?", "Sekhmet","Isis","Horus","Sekhmet",chapter3));
        holdQuestions.put(3004,new Questions("What did the Egyptians keep in their canopic jars?", "Spices","Wine","Human Organs","Human Organs",chapter3));
        holdQuestions.put(3005,new Questions("Why did the Egyptians make mummies out of dead bodies?", "honor gods","remember the dead","preserve for afterlife","preserve for afterlife",chapter3));
        holdQuestions.put(3006,new Questions("What was the role of the Egyptian god Anubis?", "providing rain","protecting the dead","judge the dead","protecting the dead",chapter3));
        holdQuestions.put(3007,new Questions("The Egyptians settled next to which river?", "Nile River","Euphrates River","Tigris River","Nile River",chapter3));

        holdQuestions.put(3008,new Questions("Where was Ancient Egypt located?", "Northeast Africa","Southwest Asia","Southeast Africa","Northeast Africa",chapter3));
        holdQuestions.put(3009,new Questions("What was a Pharaoh?", "Temple","Leader of Egypt","Egyptian diety","Leader of Egypt",chapter3));
        holdQuestions.put(3010,new Questions("Ozymandias is the Greek name for which Egyptian leader?", "Ramesses II","Amenhotep","Khafre ","Ramesses II",chapter3));
        holdQuestions.put(3011,new Questions("Which animals were seen as magical and sacred in Ancient Egypt?", "Cats","Dogs","Horses","Cats",chapter3));
        holdQuestions.put(3012,new Questions("Who was the Ancient Egyptian sun god, with the head of a hawk and the sun on his headdress?", "Ra","Horus","Thoth","Ra",chapter3));
        holdQuestions.put(3013,new Questions("Which organ did the Egyptians leave inside the body when mummifying, as the deceased would need it in the afterlife?", "Brain","Heart","Lungs","Heart",chapter3));
        holdQuestions.put(3014,new Questions("What would the god Osiris weigh the dead person’s heart against on a set of scales?", "feather of truth","sacred stones","soul of truth","feather of truth",chapter3));
        holdQuestions.put(3015,new Questions("The four sons of Horus were represented by animal heads. Which animal is NOT one of those heads?", "Baboon","Jackal","Cat","Cat",chapter3));

        holdQuestions.put(3016,new Questions("What was the largest temple built by Egyptians/", "Karnak","Luxor","Abu Simbel","Karnak",chapter3));
        holdQuestions.put(3017,new Questions("How old was boy-king, Tutankhamun when he died?", "18","16","14","18",chapter3));
        holdQuestions.put(3018,new Questions("What was Amon-Re (or Re, Ra) the God of?", "Sun","Moon","Afterlife","Sun",chapter3));
        holdQuestions.put(3019,new Questions("Which Egyptian deity was the God of the Afterlife?", "Osiris","Hathor","Seth","Osiris",chapter3));
        holdQuestions.put(3020,new Questions("What great find helped scholars decipher the Egyptian Hieroglyphs?", "Rosetta Stone","Tablet of Scripts","Rosetta Scrolls","Rosetta Stone",chapter3));
        holdQuestions.put(3021,new Questions("What Pharaoh built the first pyramid?", "Khufu","Menes","Djoser","Djoser",chapter3));
        holdQuestions.put(3022,new Questions("What shape formed the base of all the pyramids?", "triangle","square","rectangle","square",chapter3));
        holdQuestions.put(3023,new Questions("Approximately, how tall is the pyramid of Khufu at Giza?", "480 feet","221 feet","675 feet","480 feet",chapter3));

        holdQuestions.put(3024,new Questions("What is the oldest existing Egyptian monument?", "Great Sphinx","Great Pyramid","Abu Simbel","Great Pyramid",chapter3));
        holdQuestions.put(3025,new Questions("Egyptians used reeds to make an early form of paper called _______.", "papyrus","regent","hieroglyphs","papyrus",chapter3));
        holdQuestions.put(3026,new Questions("Most people in ancient Egypt belonged to the social class of", "peasants","merchants","artisans","peasants",chapter3));
        holdQuestions.put(3027,new Questions("What was the title given to Pharaoh’s chief advisor?", "Vizier","Visor","Noble","Vizier",chapter3));
        holdQuestions.put(3028,new Questions("The Rosetta stone had three scripts, it contained Greek, hieroglyphics and what other script?", "demotic","arabic","latin","demotic",chapter3));
        holdQuestions.put(3029,new Questions("The Great Pyramids of Giza consists of how many pyramids?", "3","4","5","3",chapter3));
        holdQuestions.put(3030,new Questions("Which pharaoh was the first historically confirmed female pharaoh?", "Sobekneferu","Nefertiti","cleopatra","Sobekneferu",chapter3));
        holdQuestions.put(3031,new Questions("Which pharaoh covered slaves in honey to attract flies away from him?", "Pharaoh Pepi II","Ramesses III","Tutankhamun","Pharaoh Pepi II",chapter3));

        holdQuestions.put(3800,new Questions("What was the capital of Egypt when the pyramids of Giza were built?", "Amarna","Memphis","Thebes","Memphis",hardChap3));
        holdQuestions.put(3801,new Questions("Which ancient Egyptian god is the sphinx most closely associated with?", "Horus","Anubis","Osiris","Horus",hardChap3));
        holdQuestions.put(3802,new Questions("Which animal was Ra's daughter the goddess Bastet?", "cat","baboon","jackal","cat",hardChap3));
        holdQuestions.put(3803,new Questions("Narmer is considered to be the founder of Egypt's...?", "first dynasty.","second dynasty.","third dynasty.","first dynasty.",hardChap3));
        holdQuestions.put(3804,new Questions("Who was Hor-Aha?", "a pharaoh","a god","a priest","a pharaoh",hardChap3));
        holdQuestions.put(3805,new Questions("One of the waterfalls along the Nile is called...", "Erawan Falls","Murchison Falls","Wachirathan Falls","Murchison Falls",hardChap3));
        holdQuestions.put(3806,new Questions("The ancient egyptians called their land...", "Kemet","Epirus","Malis","Kemet",hardChap3));
        holdQuestions.put(3807,new Questions("The entrances of pyramids faced...", "north","south","west","north",hardChap3));


        // CHAPTER 4 - ANCIENT CHINA

        holdQuestions.put(4000,new Questions("What gemstone did the Chinese esteem in higher regard than gold and silver?", "Jade","Peridot","Bloodstone","Jade",chapter4));
        holdQuestions.put(4001,new Questions("What is the Terracotta army made from?", "Straw","Clay","Stone","Clay",chapter4));
        holdQuestions.put(4002,new Questions("What mythical creature symbolized the Chinese Emperor's power?", "Dragon","Minotaur","Aqrabuamelu","Dragon",chapter4));
        holdQuestions.put(4003,new Questions("Who was credited with unifying ancient China?", "Qin Shi Huang","Wu Zetian","Taizong","Qin Shi Huang",chapter4));
        holdQuestions.put(4004,new Questions("Which river was the Shang Dynasty located next to?", "Yellow River","Red River","Dead River","Yellow River",chapter4));
        holdQuestions.put(4005,new Questions("Who was the supreme deity of the Shang people?", "Di","Tian","Fuxi","Di",chapter4));
        holdQuestions.put(4006,new Questions("Oracle bones were used for....", "divination","counting","payment","divination",chapter4));
        holdQuestions.put(4007,new Questions("Who was only allowed to communicate with the deity Di?", "King","Queen","Artisans","King",chapter4));

        holdQuestions.put(4008,new Questions("Which people overthrowed the Shang Dynasty?", "Zhou","Han","Qin","Zhou",chapter4));
        holdQuestions.put(4009,new Questions("What is the name of the famous book on battle strategy written by Sun Tzu?", "The Art of War","Three Kingdoms","Blood Meridian","The Art of War",chapter4));
        holdQuestions.put(4010,new Questions("Who was Ancient China`s main enemy that lived to the north?", "Mongols","Romans","Greeks","Mongols",chapter4));
        holdQuestions.put(4011,new Questions("What was the last dynasty to rule Ancient China?", "Han","Qing","Zhou","Qing",chapter4));
        holdQuestions.put(4012,new Questions("Where did the belief that the Chinese king’s right to rule came from the gods?", "Mandate of Heaven","Legalism","Daoism","Mandate of Heaven",chapter4));
        holdQuestions.put(4013,new Questions("Yin and Yang is an important symbol from...", "Daoism","Buddhism","Hinduism","Daoism",chapter4));
        holdQuestions.put(4014,new Questions("Which dynasty contributed the first written records?", "Shang","Han","Qin","Shang",chapter4));
        holdQuestions.put(4015,new Questions("Who was Confucius?", "King","Peasant","Teacher","Teacher",chapter4));

        holdQuestions.put(4016,new Questions("Under which Chinese dynasty was paper invented?", "Shang","Han","Ming","Han",chapter4));
        holdQuestions.put(4017,new Questions("Which dynasty first introduced the concept of a calendar?", "Shang","Han","Xia","Shang",chapter4));
        holdQuestions.put(4018,new Questions("The first Chinese writing appears on what?", "Paper","Oracle Bones","Rocks","Oracle Bones",chapter4));
        holdQuestions.put(4019,new Questions("What is a jian?", "sword","priest","gemstone","sword",chapter4));
        holdQuestions.put(4020,new Questions("Daoism is an ancient Chinese philosophy based upon the ideas of:", "Laozi","Dao","Qin","Laozi",chapter4));
        holdQuestions.put(4021,new Questions("Under the Han dynasty, society was divided into four main social classes. What was the lowest social class?", "Farmers","Craftsmen","Merchants","Merchants",chapter4));
        holdQuestions.put(4022,new Questions("Which of the following was invented by the ancient Chinese?", "Gunpowder","Wheelbarrow","Bicycle","Gunpowder",chapter4));
        holdQuestions.put(4023,new Questions("What were the clothes of ancient Chinese royalty made of?", "Silk","Hemp","Cotton","Silk",chapter4));

        holdQuestions.put(4024,new Questions("Which of the following is NOT a year in the ancient Chinese calendar?", "Tiger","Snake","Fish","Fish",chapter4));
        holdQuestions.put(4025,new Questions("Why did the early people of China settle along the Huang He(Yellow River)?", "Food and Water","Trade Route","Provided Security","Food and Water",chapter4));
        holdQuestions.put(4026,new Questions("The Han dynasty lasted between", "206 BCE – 220 C.E","2106 BCE – 111 C.E","110 BCE – 33 C.E","206 BCE – 220 C.E",chapter4));
        holdQuestions.put(4027,new Questions("What philosophy became the official state orthodoxy of the Han Dynasty?", "Confucianism","Buddhism","Daoism","Confucianism",chapter4));
        holdQuestions.put(4028,new Questions("All of the ideas of Confucius were written down by his followers in a text known as:", "Analects","Dhammapada","Vedas","Analects",chapter4));
        holdQuestions.put(4029,new Questions("Shi Huang di was emperor of which dynasty?", "Shang","Qin","Zhou","Qin",chapter4));
        holdQuestions.put(4030,new Questions("After the death of emperor Shi Huang di of the Qin dynasty, which dynasty took over?", "Zhou","Han","Ming","Han",chapter4));
        holdQuestions.put(4031,new Questions("Which river is also known as the Yellow River?", "Huang","Yangtze","Euphrates","Huang",chapter4));

        holdQuestions.put(4800,new Questions("Who was the founder and first emperor of the Han Dynasty?", "Liu Bang","Lu Shang","Mao Tse Tung","Liu Bang",hardChap4));
        holdQuestions.put(4801,new Questions("What major religion first arrived in China during the Han Dynasty?", "Taoism","Confucianism","Buddhism","Buddhism",hardChap4));
        holdQuestions.put(4802,new Questions("What dynasty came before the Han Dynasty?", "Qin","Shang","Tang","Qin",hardChap4));
        holdQuestions.put(4803,new Questions("What dynasty ruled before the Qing Dynasty?", "Shang Dynasty","Tang Dynasty","Ming Dynasty","Ming Dynasty",hardChap4));
        holdQuestions.put(4804,new Questions("What is another name for the Qing Dynasty?", "Dragon Blood","Three Kingdoms","Manchu Dynasty","Manchu Dynasty",hardChap4));
        holdQuestions.put(4805,new Questions("Who did the Chinese fight in the Opium Wars?", "Russia","Japan","Great Britain","Great Britain",hardChap4));
        holdQuestions.put(4806,new Questions("What Chinese dynasty preceded the Tang Dynasty?", "Song Dynasty","Zhou Dynasty","Sui Dynasty","Sui Dynasty",hardChap4));
        holdQuestions.put(4807,new Questions("Who was the first emperor of the Tang?", "Wuwang","Shunzhi","Li Yuan","Li Yuan",hardChap4));
        holdQuestions.put(4808,new Questions("What did the Chinese believe would help to scare off evil spirits?", "Poetry","Garlic","Fireworks","Fireworks",hardChap4));
        holdQuestions.put(4809,new Questions("Which was the capital city of the Tang Dynasty?", "Nanjing","Xianyang","Chang`an","Chang`an",hardChap4));

        //  CHAPTER 5 - ANCIENT GREECE

        holdQuestions.put(5000,new Questions("Which Persian king did the Greeks defeat at the Battle of Marathon?", "Darius","Xerxes","Cyrus","Darius",chapter5));
        holdQuestions.put(5001,new Questions("Who was Alexander the Great’s teacher?", "Ptolemy","Plato","Aristotle","Aristotle",chapter5));
        holdQuestions.put(5002,new Questions("Alexander the Great was from:", "Macedonia","Crete","Attica","Macedonia",chapter5));
        holdQuestions.put(5003,new Questions("Which Greek city is often called the \"birthplace of democracy\"?", "Athens","Sparta","Delphi","Athens",chapter5));
        holdQuestions.put(5004,new Questions("How did the philosopher Socrates die?", "poison","hanging","old age","poison",chapter5));
        holdQuestions.put(5005,new Questions("Who was the enemy of Athens in the Peloponnesian War?", "Sparta","Sicily","Sermione","Sparta",chapter5));
        holdQuestions.put(5006,new Questions("Who won the Peloponnesian War?", "Sicily","Sparta","Athens","Sparta",chapter5));
        holdQuestions.put(5007,new Questions("Which Greek hero was called \"Tamer of Horses\"?", "Hector","Alexander the Great","Apollo","Hector",chapter5));

        holdQuestions.put(5008,new Questions("Who was the chief god of the ancient Greeks?", "Zeus","Poseidon","Hermes","Zeus",chapter5));
        holdQuestions.put(5009,new Questions("Who was the Greek goddess of the rainbow?", "Iris","Hera","Aphrodite","Iris",chapter5));
        holdQuestions.put(5010,new Questions("What toy was invented by the Greeks that many children still play with today?", "yo-yo","marbles","slinky","yo-yo",chapter5));
        holdQuestions.put(5011,new Questions("What do we call the last period of Ancient Greece, before they were conquered by the Romans?", "Archaic Period","Classical Period","Hellenistic Period","Hellenistic Period",chapter5));
        holdQuestions.put(5012,new Questions("What period was marked by democracy and great philosophers such as Plato and Socrates?", "Classical Period","Hellenistic Period","Romantic Period","Classical Period",chapter5));
        holdQuestions.put(5013,new Questions("What were the two main city states of Ancient Greece?", "Sparta and Athens","Roma and Alexandria","Corinth and Thebes","Sparta and Athens",chapter5));
        holdQuestions.put(5014,new Questions("What was the prize for winning in the Olympics?", "Medal","Olive Wreath","Discus","Olive Wreath",chapter5));
        holdQuestions.put(5015,new Questions("Who wrote \"The Odyssey\"?", "Homer","Athena","Odysseus","Homer",chapter5));

        holdQuestions.put(5016,new Questions("Who was The Oracle?", "A general","A philosopher","A priestess","A priestess",chapter5));
        holdQuestions.put(5017,new Questions("According to the ancient Greeks, the gods and goddesses lived...", "Sparta","Athens","Mount Olympus","Mount Olympus",chapter5));
        holdQuestions.put(5018,new Questions("What were the largest population centers in ancient Greece called?", "City-states","Metropolises","Capitals","City-states",chapter5));
        holdQuestions.put(5019,new Questions("What was the most important city-state in ancient Greece?", "Athens","Rome","Sparta","Athens",chapter5));
        holdQuestions.put(5020,new Questions("Which massive sporting event was invented by the ancient Greeks?", "Olympics","World Cup","Super Bowl","Olympics",chapter5));
        holdQuestions.put(5021,new Questions("Which system of governance originated in ancient Greece?", "Democracy","Monarchy","Communism","Democracy",chapter5));
        holdQuestions.put(5022,new Questions("What is the name of the Greek philosopher and mathematician who invented the Pythagorean Theorem?", "Pythagoras","Archimedes","Aristotle","Pythagoras",chapter5));
        holdQuestions.put(5023,new Questions("Which mythic figure is a central character in the Iliad, known for a particular weakness in the foot area?", "Telemachus","Heracles","Achilles","Achilles",chapter5));

        holdQuestions.put(5024,new Questions("The Ancient Greeks created a new alphabet based on an earlier version of which civilisation?", "Roma","Persia","Phoenicia","Phoenicia",chapter5));
        holdQuestions.put(5025,new Questions("What did the ancient Greeks call themselves?", "Greeks","Hellenes","Hellas","Hellenes",chapter5));
        holdQuestions.put(5026,new Questions("In the Ancient Greek, what was a phalanx?", "military formation","market place","monetary unit","military formation",chapter5));
        holdQuestions.put(5027,new Questions("The Ancient Greeks did not eat beans. They believed the beans...", "contained souls","were unhealthy","were sacred","contained souls",chapter5));
        holdQuestions.put(5028,new Questions("What is known as the basic unit in the early society of the Athena?", "Oikos","Genos","Phratry","Oikos",chapter5));
        holdQuestions.put(5029,new Questions("Who is called the \"father of history.\"", "Herodotus","Diogenes","Plato","Herodotus",chapter5));
        holdQuestions.put(5030,new Questions("The Stoics were led by", "Aristotle","Zeno","Epicurus","Zeno",chapter5));
        holdQuestions.put(5031,new Questions("Which of Alexander's generals came to rule Egypt?", "Ptolemy","Odysseus","Antigonus","Ptolemy",chapter5));

        holdQuestions.put(5800,new Questions("What were the Ancient Greek tunics called?", "chitons","togas","kirtle","chitons",hardChap5));
        holdQuestions.put(5801,new Questions("Who built the Trojan Horse?", "Trojans","Greeks","Athena","Greeks",hardChap5));
        holdQuestions.put(5802,new Questions("The Ancient Greek city Mycenae was guarded by two stone carvings of which animals?", "Lions","Bears","Elephants","Lions",hardChap5));
        holdQuestions.put(5803,new Questions("Which distinguished historian is well-known for chronicling the Peloponnesian War?", "Xenophon","Herodotus","Thucydides","Thucydides",hardChap5));
        holdQuestions.put(5804,new Questions("Which major civilisation was significantly influenced by Greek culture and came soon after the Ancient Greek?", "Egyptian","Persian","Roman","Roman",hardChap5));
        holdQuestions.put(5805,new Questions("The Ancient Greek warships were called...", "Quadrimes","Triremes","Quadriremes","Triremes",hardChap5));
        holdQuestions.put(5806,new Questions("The Parthenon is the most famous ancient Greek building. What kind of building was it?", "Palace","Theatre","Temple","Temple",hardChap5));
        holdQuestions.put(5807,new Questions("What was the central marketplace in ancient Greece?", "Agora","Polis","Archon","Agora",hardChap5));
        holdQuestions.put(5808,new Questions("Which was based on a religious festival that payed homage to the God Zeus?", "Olympics","Tributes","Delian League","Olympics",hardChap5));
        holdQuestions.put(5809,new Questions("What was the greek word for city-state?", "polis","naos","thánatos","polis",hardChap5));

        // CHAPTER 6 - ROMANS

        holdQuestions.put(6000,new Questions("Which was the first Roman road?", "Via Appia","Via Valeria","Via Egnatia","Via Appia",chapter6));
        holdQuestions.put(6001,new Questions("Which of the following did not defeat Mithridates VI of Pontus?", "Pompey","Sulla","Marius","Marius",chapter6));
        holdQuestions.put(6002,new Questions("In what year did the Western Roman Empire come to an end?", "476 CE","564 CE","399 CE","476 CE",chapter6));
        holdQuestions.put(6003,new Questions("Which of these did the Romans not employ in battle?", "submarines","steel bolts","missile launchers","submarines",chapter6));
        holdQuestions.put(6004,new Questions("Who was a legendary founder of Rome?", "Romulus","Claudius","Julius Caesar","Romulus",chapter6));
        holdQuestions.put(6005,new Questions("Which one of these was not implicated in the assassination of Julius Caesar?", "Brutus","Nero","Cassius","Nero",chapter6));
        holdQuestions.put(6006,new Questions("How many hills was Rome built on?", "5","4","7","7",chapter6));
        holdQuestions.put(6007,new Questions("When did Julius Caesar die?", "44 CE","44 BCE","22 CE","44 BCE",chapter6));

        holdQuestions.put(6008,new Questions("What was the marketplace and meeting area called in Roman cities?", "forum","capital","fort","forum",chapter6));
        holdQuestions.put(6009,new Questions("Who was the emperor during the first year of the Pax Romana?", "Augustus","Galba","Tiberius","Augustus",chapter6));
        holdQuestions.put(6010,new Questions("Which dynasty was founded by Vespasian?", "Flavian dynasty","Severan dynasty","Decian dynasty","Flavian dynasty",chapter6));
        holdQuestions.put(6011,new Questions("Who wrote the Germania?", "Tacitus","Suetonius","Sallust","Tacitus",chapter6));
        holdQuestions.put(6012,new Questions("Which Roman governor commanded the crucifixion of Jesus?", "Pontius Pilate","Lucius Verus","Aulus Plautius","Pontius Pilate",chapter6));
        holdQuestions.put(6013,new Questions("Whom did Constantius II defeat in the Battle of Mursa?", "Allectus","Magnentius","Eugenius","Magnentius",chapter6));
        holdQuestions.put(6014,new Questions("Which Roman emperor was the first to profess Christianity?", "Constantine I","Maximinus Thrax","Severus Alexander","Constantine I",chapter6));
        holdQuestions.put(6015,new Questions("How many books are there in the Code of Justinian?", "4","2","9","4",chapter6));

        holdQuestions.put(6016,new Questions("Which piece of Roman architecture was built under the Flavian emperors, during the reign of Vespasian?", "Pantheon","Pula Arena","Colosseum","Colosseum",chapter6));
        holdQuestions.put(6017,new Questions("Which ancient Roman city was destroyed after the eruption of Mount Vesuvius?", "Pompeii","Aquileia","Amphissa","Pompeii",chapter6));
        holdQuestions.put(6018,new Questions("Which was the privileged social class in ancient Roman society?", "patrician","plebeian","eques","patrician",chapter6));
        holdQuestions.put(6019,new Questions("Which Roman emperor self-adopted a title that means \"restorer of the world\"?", "Aurelian","Valerian","Saloninus","Aurelian",chapter6));
        holdQuestions.put(6020,new Questions("What was the eastern half of the Roman Empire called?", "Byzantine Empire","Ottoman Empire","Gaza Empire","Byzantine Empire",chapter6));
        holdQuestions.put(6021,new Questions("What were the household troops of the Roman emperors called?", "Praetorian Guard","Roman Elite","Byzantine Guard","Praetorian Guard",chapter6));
        holdQuestions.put(6022,new Questions("Who adopted the name Marcus Aurelius as a part of his imperial title?", "Macrinus","Carus","Diadumenian","Carus",chapter6));
        holdQuestions.put(6023,new Questions("How many Punic Wars were there?", "3","8","1","3",chapter6));

        holdQuestions.put(6024,new Questions("Which city was destroyed as a result of the First Punic War?", "Carthage","Cannae","Sevilla","Carthage",chapter6));
        holdQuestions.put(6025,new Questions("In which war did Hannibal cross the Alps?", "First Punic War","Second Punic War","Chremonidean War","Second Punic War",chapter6));
        holdQuestions.put(6026,new Questions("What leader became the dictator of Rome and put an end to the Roman Republic?", "Nero","Caesar Augustus","Julius Caesar","Julius Caesar",chapter6));
        holdQuestions.put(6027,new Questions("What two languages did many Romans speak?", "Latin and Spanish","Latin and Greek","Greek and Italian","Latin and Greek",chapter6));
        holdQuestions.put(6028,new Questions("What was the highest position in the Roman Republic?", "Consol","Emperor","Captain","Consol",chapter6));
        holdQuestions.put(6029,new Questions("What structural material did the Roman engineers use to construct the Colosseum and the Pantheon?", "Concrete","Marble","Granite","Concrete",chapter6));
        holdQuestions.put(6030,new Questions("What group of people were known as the Patricians?", "Upper Class","Lower Class","Military Leaders","Upper Class",chapter6));
        holdQuestions.put(6031,new Questions("What was the earliest written legislation of ancient Roman law called?", "Code of Hammurabi","The Twelve Tables","Liber Judiciorum","The Twelve Tables",chapter6));

        holdQuestions.put(6800,new Questions("What is probably the only ancient Roman lighthouse still in use?", "Tower of Hercules","Pharos of Alexandria","Tower of Babel","Tower of Hercules",hardChap6));
        holdQuestions.put(6801,new Questions("Which Roman leader reformed the calendar?", "Julius Caesar","Constantine","Nero","Julius Caesar",hardChap6));
        holdQuestions.put(6802,new Questions("Which Roman leader’s failed revolt led to 6,000 of his followers being crucified?", "Catiline","Brutus","Spartacus","Spartacus",hardChap6));
        holdQuestions.put(6803,new Questions("Which ancient military commander had at an early age been made to swear eternal hostility to Rome?", "Pyrrhus","Alexander the Great","Hannibal","Hannibal",hardChap6));
        holdQuestions.put(6804,new Questions("Which battle marked the beginning of serious Germanic inroads into Roman territory?", "Battle of Adrianople","Battle of Milvian ","Battle of Tolbiac","Battle of Adrianople",hardChap6));
        holdQuestions.put(6805,new Questions("Which battle caused the decisive defeat of Mark Antony and Cleopatra?", "Battle of Actium","Battle of Munda","Battle of Pharsalus","Battle of Actium",hardChap6));
        holdQuestions.put(6806,new Questions("Which conqueror immortalized the words \"Veni, vidi, vici\"?", "Nero","Julius Caesar","Augustus","Julius Caesar",hardChap6));
        holdQuestions.put(6807,new Questions("According to Roman tradition, where does the name for Rome come from?", "Romulus","Romoro","Romelus","Romulus",hardChap6));
        holdQuestions.put(6808,new Questions("Who ruled Rome before the Roman Republic was formed?", "Kings","Tyrants","Dictators","Kings",hardChap6));
        holdQuestions.put(6809,new Questions("Who ruled Rome during the Roman Republic?", "Emperors","Kings","Dictators","Emperors",hardChap6));

        // CHAPTER 7 - TARASCAN/PURéPECHA EMPIRE

        holdQuestions.put(7000,new Questions("What lake was located near the center of the Tarascan Empire?", "Lake Pátzcuaro","Lake Texcoco","Lake Titicaca","Lake Pátzcuaro",chapter7));
        holdQuestions.put(7001,new Questions("What language did the people of Tarascan Empire speak?", "Malinke-Bambara","Purépecha","Quechuan","Purépecha",chapter7));
        holdQuestions.put(7002,new Questions("Which people gave the Tarascan people their name?", "French","Spanish","Portuguese","Spanish",chapter7));
        holdQuestions.put(7003,new Questions("Which city was the center of the Tarascan Empire?", "Tzintzúntzan","Angamuco","Pátzcuaro","Tzintzúntzan",chapter7));
        holdQuestions.put(7004,new Questions("Who was the last monarch of the Tarascan state?", "Tzimtzincha-Tangaxuan II","Tzitzipandáquare","Hiquingaje","Tzimtzincha-Tangaxuan II",chapter7));
        holdQuestions.put(7005,new Questions("Which Spanish conquistador executed Tarascan monarch Tzimtzincha-Tangaxuan II?", "Nuño de Guzmán","Hernán Cortés","Francisco Pizarro","Nuño de Guzmán",chapter7));
        holdQuestions.put(7006,new Questions("The Tarascans constantly fought with their rival enemies, the..", "Aztecs","Incas","Olmec","Aztecs",chapter7));
        holdQuestions.put(7007,new Questions("According to the Relación de Michoacán, how much groups were the Tarascan nobility divided into?", "2","3","6","3",chapter7));

        holdQuestions.put(7008,new Questions("In the Tarascan religion, they believed the universe comprised of the sky, earth and.....", "the underworld","the pit","the gods","the underworld",chapter7));
        holdQuestions.put(7009,new Questions("The Tarascans believed the sky was ruled by the deity....", "Kurikaweri","Curicaueri","Cuitzeo","Kurikaweri",chapter7));
        holdQuestions.put(7010,new Questions("The use of military strategies and the use of __________ helped the Tarascan state remained unconquered by the Aztecs?", "metal weapons","their gods","large walls","metal weapons",chapter7));
        holdQuestions.put(7011,new Questions("The Tarascans were one of the few Mesoamerican civilisations to use _________ as tools and ornamentation.", "metal","wood","stones","metal",chapter7));
        holdQuestions.put(7012,new Questions("The Tzintzuntzan capital city comes the Purépecha word Ts’intsuntsani. What is the meaning of Ts’intsuntsani?", "place of hummingbirds","place of demons","land of fertility","place of hummingbirds",chapter7));
        holdQuestions.put(7013,new Questions("Yácata pyramids in Tzintzuntzan were..... ", "circular","triangular","rectangular","circular",chapter7));
        holdQuestions.put(7014,new Questions("Why is little known about the cultural history of the Tarascan people?", "no written records","not relevant","lack of culture","no written records",chapter7));
        holdQuestions.put(7015,new Questions("Around 1540, Franciscan priest Fray Jeronimo de Acalá compiled and wrote much of Tarascan history. What is the name of this book?", "Relación de Michoacán","Lienzo de Jucutacuto","Tariacurl's Legacy","Relación de Michoacán",chapter7));

        holdQuestions.put(7800,new Questions("What title was given to rulers/kings of Purépecha?", "","Kasonsí","Tlahtoani","Kasonsí",hardChap7));
        holdQuestions.put(7801,new Questions("The Purépechas settled around which mountains?", "Sierra Madre","","","Sierra Madre",hardChap7));
        holdQuestions.put(7802,new Questions("The Tarascans were known to be masters at catching...", "fish","monkeys","deer","fish",hardChap7));
        holdQuestions.put(7803,new Questions("The Purépechas share linguistic similarities to which South American language?", "","","Quechua","Quechua",hardChap7));
        holdQuestions.put(7804,new Questions("Which Aztec king led army towards the Tarascan people in 1478?", "","Axayacatl","","Axayacatl",hardChap7));


        // CHAPTER 8 - RANDOM

        holdQuestions.put(8000,new Questions("The Indus Valley was located closely to which river?", "Indus River","Kabul River","Neelam River","Indus River",chapter8));
        holdQuestions.put(8001,new Questions("According to new research the largest city of the Indus valley was....", "Mohenjo-daro","Harappa","Rakhigarhi","Rakhigarhi",chapter8));
        holdQuestions.put(8002,new Questions("People of the Indus valley placed important public structures on the citadel because the citadel protected them from enemies and", "flood waters","Disease","nomads","flood waters",chapter8));
        holdQuestions.put(8003,new Questions("What was the economy of the early Indian civilization based on?", "Warfare","Agriculture only","Agriculture and trade","Agriculture and trade",chapter8));
        holdQuestions.put(8004,new Questions("Which people gave the Tarascan people their name?", "French","Spanish","Portuguese","Spanish",chapter8));
        holdQuestions.put(8005,new Questions("Which city was the center of the Tarascan Empire?", "Tzintzúntzan","Angamuco","Pátzcuaro","Tzintzúntzan",chapter8));
        holdQuestions.put(8006,new Questions("What group of people were known as the Patricians?", "Upper Class","Lower Class","Military Leaders","Upper Class",chapter8));
        holdQuestions.put(8007,new Questions("What was the earliest written legislation of ancient Roman law called?", "Code of Hammurabi","The Twelve Tables","Liber Judiciorum","The Twelve Tables",chapter8));

        holdQuestions.put(8008,new Questions("How many hills was Rome built on?", "5","4","7","7",chapter8));
        holdQuestions.put(8009,new Questions("When did Julius Caesar die?", "44 CE","44 BCE","22 CE","44 BCE",chapter8));
        holdQuestions.put(8010,new Questions("What is the name of the Greek philosopher and mathematician who invented the Pythagorean Theorem?", "Pythagoras","Archimedes","Aristotle","Pythagoras",chapter8));
        holdQuestions.put(8011,new Questions("Which mythic figure is a central character in the Iliad, known for a particular weakness in the foot area?", "Telemachus","Heracles","Achilles","Achilles",chapter8));
        holdQuestions.put(8012,new Questions("What lake was located near the center of the Tarascan Empire?", "Lake Pátzcuaro","Lake Texcoco","Lake Titicaca","Lake Pátzcuaro",chapter8));
        holdQuestions.put(8013,new Questions("What language did the people of Tarascan Empire speak?", "Malinke-Bambara","Purépecha","Quechuan","Purépecha",chapter8));
        holdQuestions.put(8014,new Questions("After the death of emperor Shi Huang di of the Qin dynasty, which dynasty took over?", "Zhou","Han","Ming","Han",chapter8));
        holdQuestions.put(8015,new Questions("Which river is also known as the Yellow River?", "Huang","Yangtze","Euphrates","Huang",chapter8));

        holdQuestions.put(8016,new Questions("What was the title given to Pharaoh’s chief advisor?", "Vizier","Visor","Noble","Vizier",chapter8));
        holdQuestions.put(8017,new Questions("The Rosetta stone had three scripts, it contained Greek, hieroglyphics and what other script?", "demotic","arabic","latin","demotic",chapter8));
        holdQuestions.put(8018,new Questions("Which Egyptian deity was the God of the Afterlife?", "Osiris","Hathor","Seth","Osiris",chapter8));
        holdQuestions.put(8019,new Questions("What great find helped scholars decipher the Egyptian Hieroglyphs?", "Rosetta Stone","Tablet of Scripts","Rosetta Scrolls","Rosetta Stone",chapter8));
        holdQuestions.put(8020,new Questions("What was the significance of the ziggurat?", "temples for gods","graves for dead","pool","temples for gods",chapter8));
        holdQuestions.put(8021,new Questions("Who was Tiglath-Pileser I?", "King of Assyria","Sumerian writer","Artist","King of Assyria",chapter8));
        holdQuestions.put(8022,new Questions("What does the Sumerian term Lugal mean?", "Slave","Ruler","Philosopher","Ruler",chapter8));
        holdQuestions.put(8023,new Questions("Cuneiform writing provided the basis for the development of what?", "recorded history","paintings","traditions","recorded history",chapter8));

        holdQuestions.put(8024,new Questions("What material did the Indus Valley people use to make cloth?", "Cotton","Wheat","Barley","Cotton",chapter8));
        holdQuestions.put(8025,new Questions("Jewelery recovered from the Indus Valley was made with what stone?", "Carnelian","Ruby","Sapphire","Carnelian",chapter8));
        holdQuestions.put(8026,new Questions("What was the first Indus Valley city to be discovered?", "Mohenjo-daro","Kalibangan","Harappa","Harappa",chapter8));
        holdQuestions.put(8027,new Questions("What have been found in the Indus Valley with pictures and writing on them?", "scrolls","tablets","seals","seals",chapter8));
        holdQuestions.put(8028,new Questions("How many laws are in the Hammurabi code of laws?", "369","282","410","282",chapter8));
        holdQuestions.put(8029,new Questions("Where did people go to learn reading, writing, religion, law, medicine and even astrology in Mesopotamia?", "temples","schools","churches","schools",chapter8));
        holdQuestions.put(8030,new Questions("The Mesopotamians believed that they were what with the gods?", "co-laborers","subservient","equal to","co-laborers",chapter8));
        holdQuestions.put(8031,new Questions("What was the main system of Mesopotamia?", "Polytheism","Monotheism","Atheism","Polytheism",chapter8));

        holdQuestions.put(8800,new Questions("Which faction in the Roman Senate were concerned with protecting the elite?", "Optimates","Equites","Populares","Optimates",hardChap8));
        holdQuestions.put(8801,new Questions("In early Roman history, which social class largely comprised the Roman Senate?", "","","","",hardChap8));
        holdQuestions.put(8802,new Questions("Which of the following were weapons of war in ancient Mesopotamia?", "Spears","Axes","Both are correct","Both are correct",hardChap8));
        holdQuestions.put(8803,new Questions("Where was the Empire of Mali located?", "West Africa","South Africa","East Africa","West Africa",hardChap8));
        holdQuestions.put(8804,new Questions("What natural resource came from the mines south of Mali?", "Salt","Gold","Lead","Gold",hardChap8));
        holdQuestions.put(8805,new Questions("Which dynasty was founded by Caesar Augustus?", "Antonine","Flavians","Julio-Claudians","Julio-Claudians",hardChap8));
        holdQuestions.put(8806,new Questions("What Roman General defeated Hannibal at the Battle of Zama?", "Marcus Antonius","Gaius Marius","Scipio Africanus","Scipio Africanus",hardChap8));
        holdQuestions.put(8807,new Questions("Where were hieroglyphics used?", "Ancient Egypt","Ancient Mesopotamia","Ancient China","",hardChap8));
        holdQuestions.put(8808,new Questions("The Elgin Marbles were taken from which building?", "Parthenon","Luxor","Temple of Zeus","Parthenon",hardChap8));
        holdQuestions.put(8809,new Questions("The earliest city found in India is...", "Punjab","Mohenjo-daro","Harappa","Harappa",hardChap8));


    }
/*
    private void religion_quiz() {

        //CHAPTER 1 - GREEK MYTHS

        holdQuestions.put(,new Questions("Which of the following countries claims a majority Hindu presence?", "Nepal","China","Malaysia","Nepal",chapter1));
        holdQuestions.put(,new Questions("Which of the following is not an incarnation of Vishnu?", "Nataraja","Vamana","Krishna","Nataraja",chapter1));
        holdQuestions.put(,new Questions("Which of the following deities is the cosmic dancer through the universe’s creation and destruction?", "Shiva","Brahma","Ganesh","Shiva",chapter1));
        holdQuestions.put(,new Questions("Which of the following festivals is associated with the goddess Lakshmi and is a time for the lighting of festive lamps and of fireworks displays?", "Diwali","Holi","Kumbh Mela","Diwali",chapter1));
        holdQuestions.put(,new Questions("", "","","","",chapter1));
        holdQuestions.put(,new Questions("", "","","","",chapter1));
        holdQuestions.put(,new Questions("", "","","","",chapter1));
        holdQuestions.put(,new Questions("", "","","","",chapter1));


        holdQuestions.put(1,new Questions("Square root of 25?", "5","7","6","a written form of the Egyptian language",chapter1));
        holdQuestions.put(2,new Questions("", "Egypt","India","Saudi Arabia","a written form of the Egyptian language",chapter1));
        holdQuestions.put(3,new Questions("What is the largest pyramid ever constructed?", "Great Pyramid of Khufu","Red Pyramid","Quetzalcóatl Pyramid","a written form of the Egyptian language",chapter1));
        holdQuestions.put(4,new Questions("Where was the ancient Indus Valley Civilization located?", "Africa","India","Mexico","a written form of the Egyptian language",chapter1));
        holdQuestions.put(5,new Questions("What was the use of oracle bones in the Shang Dantasy?", "used in ritual sacrifices","to foretell the future","as money","a written form of the Egyptian language",chapter1));

    }

 */

    private void math_quiz() {

        // CHAPTER 1 - ADDITION
        holdQuestions.put(1000,new Questions("10 + 5", "15","12","13","15",chapter1));
        holdQuestions.put(1001,new Questions("33 + 13", "49","46","56","46",chapter1));
        holdQuestions.put(1002,new Questions("21 + 18", "39","38","37","39",chapter1));
        holdQuestions.put(1003,new Questions("17 + 96", "113","114","112","113",chapter1));
        holdQuestions.put(1004,new Questions("2.5 + 3.6", "6.01","6.1","6","6.1",chapter1));
        holdQuestions.put(1005,new Questions("23.4 + 12.3", "35.7","34.7","36.7","35.7",chapter1));
        holdQuestions.put(1006,new Questions("110 + 240", "350","330","370","350",chapter1));
        holdQuestions.put(1007,new Questions("547 + 133", "680","670","681","680",chapter1));

        holdQuestions.put(1008,new Questions("49 + 47", "96","93","95","96",chapter1));
        holdQuestions.put(1009,new Questions("50 + 51", "101","99","104","101",chapter1));
        holdQuestions.put(1010,new Questions("33.3 + 93.9", "127.2","126.2","127.6","127.2",chapter1));
        holdQuestions.put(1011,new Questions("8.1 + 176.78", "184.88","189.88","183.88","184.88",chapter1));
        holdQuestions.put(1012,new Questions("300 + 120", "420","410","440","420",chapter1));
        holdQuestions.put(1013,new Questions("417 + 189", "607","599","606","606",chapter1));
        holdQuestions.put(1014,new Questions("12 + 29", "41","43","38","41",chapter1));
        holdQuestions.put(1015,new Questions("21 + 64", "85","81","75","85",chapter1));

        holdQuestions.put(1016,new Questions("1010 + 2302", "3312","3112","3212","3312",chapter1));
        holdQuestions.put(1017,new Questions("3419 + 5894", "9313","9213","9323","9313",chapter1));
        holdQuestions.put(1018,new Questions("3.9 + 3.8", "7.7","7.9","8.7","7.7",chapter1));
        holdQuestions.put(1019,new Questions("0.1 + 8.9", "9","8.91","9.01","9",chapter1));
        holdQuestions.put(1020,new Questions("173 + 292", "455","465","463","465",chapter1));
        holdQuestions.put(1021,new Questions("485 + 634", "1117","1229","1119","1119",chapter1));
        holdQuestions.put(1022,new Questions("41 + 61.5", "102.5","103.5","101.5","102.5",chapter1));
        holdQuestions.put(1023,new Questions("21 + 34.9", "55.9","53.9","54.9","55.9",chapter1));

        holdQuestions.put(1024,new Questions("34 + 13", "57","44","47","47",chapter1));
        holdQuestions.put(1025,new Questions("91 + 46", "137","127","139","137",chapter1));
        holdQuestions.put(1026,new Questions("31.3 + 12.5", "43.8","49.8","41.8","43.8",chapter1));
        holdQuestions.put(1027,new Questions("999.1 + 19.3", "1018.4","1028.4","1016.4","1018.4",chapter1));
        holdQuestions.put(1028,new Questions("123 + 456", "579","569","589","579",chapter1));
        holdQuestions.put(1029,new Questions("321 + 654", "975","966","955","975",chapter1));
        holdQuestions.put(1030,new Questions("15 + 67", "82","83","86","82",chapter1));
        holdQuestions.put(1031,new Questions("0.1 + 0.5", "0.06","0.6","0.51","0.6",chapter1));

        //HARD CHAP 1 - ADDITION
        holdQuestions.put(1800,new Questions("123.765 + 314.81", "438.275","438.575","437.575","438.575",hardChap1));
        holdQuestions.put(1801,new Questions("49.346 + 36.5931", "83.9321","85.9391","83.9361","85.9391",hardChap1));
        holdQuestions.put(1802,new Questions("83.43 + 98.93", "182.26","181.86","182.36","182.36",hardChap1));
        holdQuestions.put(1803,new Questions("34.29 + 72.78", "107.07","109.17","107.17","107.07",hardChap1));
        holdQuestions.put(1804,new Questions("63.32 + 23.45 + 12.92", "99.69","100.19","99.89","99.69",hardChap1));
        holdQuestions.put(1805,new Questions("10.11 + 17.54 + 19.73", "47.18","47.38","47.28","47.38",hardChap1));
        holdQuestions.put(1806,new Questions("2941.82 + 181.77", "3143.19","3123.59","3127.29","3123.59",hardChap1));
        holdQuestions.put(1807,new Questions("145.87 + 231.63", "377.5","372.1","370.1","377.5",hardChap1));
        holdQuestions.put(1808,new Questions("67.34 + 12.24 + 24.65", "104.13","104.23","101.23","104.23",hardChap1));


        // CHAPTER 2 - SUBTRACTION
        holdQuestions.put(2000,new Questions("1 - 2", "-1","0","1","-1",chapter2));
        holdQuestions.put(2001,new Questions("7 - 11", "-4","4","-3","-4",chapter2));
        holdQuestions.put(2002,new Questions("21 - 11", "10","11","9","10",chapter2));
        holdQuestions.put(2003,new Questions("64 - 46", "12","18","16","18",chapter2));
        holdQuestions.put(2004,new Questions("110 - 19", "91","89","81","91",chapter2));
        holdQuestions.put(2005,new Questions("17 - 17", "0.1","0","1","0",chapter2));
        holdQuestions.put(2006,new Questions("3 - 5", "-2","-1","-3","-2",chapter2));
        holdQuestions.put(2007,new Questions("42 - 12", "32","28","30","30",chapter2));

        holdQuestions.put(2008,new Questions("99 - 17", "79","80","82","82",chapter2));
        holdQuestions.put(2009,new Questions("21 - 5", "18","16","14","16",chapter2));
        holdQuestions.put(2010,new Questions("49 - 71", "-22","22","-23","-22",chapter2));
        holdQuestions.put(2011,new Questions("123 - 109", "14","16","18","14",chapter2));
        holdQuestions.put(2012,new Questions("456 - 124", "332","322","331","332",chapter2));
        holdQuestions.put(2013,new Questions("135 - 34.2", "100.8","100.2","99.8","100.8",chapter2));
        holdQuestions.put(2014,new Questions("12.3 - 12.1", "0.2","0.02","0.12","0.2",chapter2));
        holdQuestions.put(2015,new Questions("5.6 - 2.7", "2.9","2.1","3.4","2.9",chapter2));

        holdQuestions.put(2016,new Questions("67.7 - 69.3", "-1.6","-1.4","-2.6","-1.6",chapter2));
        holdQuestions.put(2017,new Questions("74 - 56", "18","14","16","18",chapter2));
        holdQuestions.put(2018,new Questions("100 - 32", "58","66","68","68",chapter2));
        holdQuestions.put(2019,new Questions("29 - 1.1", "27.1","27.9","28.9","27.9",chapter2));
        holdQuestions.put(2020,new Questions("12 - 21", "-9","-10","-7","-9",chapter2));
        holdQuestions.put(2021,new Questions("83 - 41", "43","32","42","42",chapter2));
        holdQuestions.put(2022,new Questions("31 - 45", "-16","-12","-14","-14",chapter2));
        holdQuestions.put(2023,new Questions("57 - 59.6", "-2.4","-2.6","-2.8","-2.6",chapter2));

        holdQuestions.put(2024,new Questions("12 - 34", "-16","-22","-32","-22",chapter2));
        holdQuestions.put(2025,new Questions("21 - 6", "15","14","16","15",chapter2));
        holdQuestions.put(2026,new Questions("765 - 712", "43","53","51","53",chapter2));
        holdQuestions.put(2027,new Questions("210 - 169", "41","45","39","41",chapter2));
        holdQuestions.put(2028,new Questions("92 - 100", "-18","-8","-.8","-8",chapter2));
        holdQuestions.put(2029,new Questions("348 - 234", "114","116","118","114",chapter2));
        holdQuestions.put(2030,new Questions("78 - 32", "44","46","48","46",chapter2));
        holdQuestions.put(2031,new Questions("90 - 123.4", "-33.8","-33.6","-33.4","-33.4",chapter2));

        holdQuestions.put(2032,new Questions("367 - 139", "228","224","226","228",chapter2));
        holdQuestions.put(2033,new Questions("10 - 9.6", "1.4","1.6","0.4","0.4",chapter2));
        holdQuestions.put(2034,new Questions("23 - 0.3", "22.7","23.3","23.7","22.7",chapter2));
        holdQuestions.put(2035,new Questions("41.4 - 67", "-24.4","-25.6","-25.4","-25.6",chapter2));
        holdQuestions.put(2036,new Questions("80 - 93", "-12","-11","-13","-13",chapter2));
        holdQuestions.put(2037,new Questions("1 - 1.7", "-1.7","-0.7","-0.3","-0.7",chapter2));
        holdQuestions.put(2038,new Questions("15 - 45", "-30","-45","-20","-30",chapter2));
        holdQuestions.put(2039,new Questions("62 - 16", "46","44","48","46",chapter2));

        //HARD CHAP 2 - SUBTRACTION
        holdQuestions.put(2800,new Questions("32.44 - 69.87", "-37.43","-39.33","-37.53","-37.43",hardChap2));
        holdQuestions.put(2801,new Questions("12.12 - 6.76 - 4.23", "1.43","1.13","1.33","1.13",hardChap2));
        holdQuestions.put(2802,new Questions("456.35 - 413.91", "42.44","41.64","39.34","42.44",hardChap2));
        holdQuestions.put(2803,new Questions("321.84 - 432.65", "-111.61","-109.71","-110.81","-110.81",hardChap2));
        holdQuestions.put(2804,new Questions("145.56 - 321.62", "-176.04","-176.06","-172.16","-176.06",hardChap2));
        holdQuestions.put(2805,new Questions("671.09 - 734.77", "-63.68","-65.42","-63.18","-63.68",hardChap2));
        holdQuestions.put(2806,new Questions("93.64 - 62.89", "30.15","31.65","30.75","30.75",hardChap2));
        holdQuestions.put(2807,new Questions("25.54 - 43.67", "-18.13","-18.23","-18.03","-18.13",hardChap2));
        holdQuestions.put(2808,new Questions("99.62 - 72.86", "24.16","25.86","26.76","26.76",hardChap2));
        holdQuestions.put(2809,new Questions("946.35 - 762.68", "193.67","171.47","183.67","183.67",hardChap2));


        // CHAPTER 3 - MULTIPLICATION
        holdQuestions.put(3000,new Questions("40 x 2", "42","60","80","80",chapter3));
        holdQuestions.put(3001,new Questions("80 x 3", "240","260","160","240",chapter3));
        holdQuestions.put(3002,new Questions("6 x 6", "30","36","32","36",chapter3));
        holdQuestions.put(3003,new Questions("7 x 9", "63","72","54","63",chapter3));
        holdQuestions.put(3004,new Questions("12 x 12", "144","132","156","144",chapter3));
        holdQuestions.put(3005,new Questions("12 x 11", "132","122","136","132",chapter3));
        holdQuestions.put(3006,new Questions("13 x 11", "136","133","143","143",chapter3));
        holdQuestions.put(3007,new Questions("5 x 6", "35","30","32","30",chapter3));

        holdQuestions.put(3008,new Questions("20 x 20", "440","4000","400","400",chapter3));
        holdQuestions.put(3009,new Questions("10 x 10", "100","110","1000","100",chapter3));
        holdQuestions.put(3010,new Questions("10 x 30", "3000","300","330","300",chapter3));
        holdQuestions.put(3011,new Questions("2 x 160", "320","360","420","320",chapter3));
        holdQuestions.put(3012,new Questions("14 x 10", "120","1400","140","140",chapter3));
        holdQuestions.put(3013,new Questions("8 x 9", "89","72","81","72",chapter3));
        holdQuestions.put(3014,new Questions("7 x 7", "49","56","57","49",chapter3));
        holdQuestions.put(3015,new Questions("11 x 11", "132","111","121","121",chapter3));

        holdQuestions.put(3016,new Questions("55 x 6", "330","300","260","330",chapter3));
        holdQuestions.put(3017,new Questions("12 x 18", "216","218","246","216",chapter3));
        holdQuestions.put(3018,new Questions("2.3 x 2", "4.6","4.2","6.2","4.2",chapter3));
        holdQuestions.put(3019,new Questions("1.2 x 2.9", "3.6","3.48","3.28","3.48",chapter3));
        holdQuestions.put(3020,new Questions("15 x 5", "90","60","75","75",chapter3));
        holdQuestions.put(3021,new Questions("8 x 3", "24","32","28","24",chapter3));
        holdQuestions.put(3022,new Questions("19 x 4", "76","69","72","76",chapter3));
        holdQuestions.put(3023,new Questions("10 x 4.6", "40.6","46","46.6","46",chapter3));

        holdQuestions.put(3024,new Questions("2.7 x 9.4", "26.34","25.38","23.46","25.38",chapter3));
        holdQuestions.put(3025,new Questions("16 x 9", "136","128","144","144",chapter3));
        holdQuestions.put(3026,new Questions("10 x 10.9", "109","100.9","190","109",chapter3));
        holdQuestions.put(3027,new Questions("12.3 x 6.1", "76.03","75.03","75.3","75.03",chapter3));
        holdQuestions.put(3028,new Questions("300 x 100", "300,000","30,000","3000","30,000",chapter3));
        holdQuestions.put(3029,new Questions("250 x 100", "25,000","20,000","250,000","25,000",chapter3));
        holdQuestions.put(3030,new Questions("46 x 4", "142","184","146","184",chapter3));
        holdQuestions.put(3031,new Questions("20 x 12", "220","240","244","240",chapter3));

        //HARD CHAP 3 - MULTIPLICATION
        holdQuestions.put(3800,new Questions("17 x 43", "681","771","731","731",hardChap3));
        holdQuestions.put(3801,new Questions("65 x 26", "1690","1620","1750","1690",hardChap3));
        holdQuestions.put(3802,new Questions("13.7 x 13.7", "187.69","173.69","201.69","187.69",hardChap3));
        holdQuestions.put(3803,new Questions("134 x 59", "7906","7706","8006","7906",hardChap3));
        holdQuestions.put(3804,new Questions("71 x 31.1", "2208.1","2308.1","2138.1","2208.1",hardChap3));
        holdQuestions.put(3805,new Questions("54 x 73", "3842","3942","3952","3942",hardChap3));
        holdQuestions.put(3806,new Questions("71 x 47", "3337","3437","3267","3337",hardChap3));
        holdQuestions.put(3807,new Questions("3 x 5.6 x 12", "201.6","211.6","181.6","201.6",hardChap3));
        holdQuestions.put(3808,new Questions("4 x 3.9 x 7", "129.2","93.2","109.2","109.2",hardChap3));
        holdQuestions.put(3809,new Questions("5.6 x 4 x 4", "89.6","119.6","79.6","89.6",hardChap3));

        // CHAPTER 4 - DIVISION
        holdQuestions.put(4000,new Questions("100 / 10", "100","10","20","10",chapter4));
        holdQuestions.put(4001,new Questions("200 / 20", "5","20","10","10",chapter4));
        holdQuestions.put(4002,new Questions("75 / 5", "12","15","13","15",chapter4));
        holdQuestions.put(4003,new Questions("18 / 9", "4","2","3","2",chapter4));
        holdQuestions.put(4004,new Questions("980 / 2", "460","490","440","490",chapter4));
        holdQuestions.put(4005,new Questions("45 / 5", "9","12","8","9",chapter4));
        holdQuestions.put(4006,new Questions("21 / 3", "7","9","8","7",chapter4));
        holdQuestions.put(4007,new Questions("66 / 3", "23","33","22","22",chapter4));

        holdQuestions.put(4008,new Questions("64 / 8", "6","9","8","8",chapter4));
        holdQuestions.put(4009,new Questions("390 / 6", "70","65","60","65",chapter4));
        holdQuestions.put(4010,new Questions("480 / 4", "124","140","120","120",chapter4));
        holdQuestions.put(4011,new Questions("32 / 2", "16","18","14","16",chapter4));
        holdQuestions.put(4012,new Questions("12 / 1", "14.2","12","12.1","12",chapter4));
        holdQuestions.put(4013,new Questions("16 / 4", "4","6","8","4",chapter4));
        holdQuestions.put(4014,new Questions("6.6 / 2.2", "3.2","3","3.3","3",chapter4));
        holdQuestions.put(4015,new Questions("99 / 3", "33","33.3","36","33",chapter4));

        holdQuestions.put(4016,new Questions("880 / 8", "110","80","100","110",chapter4));
        holdQuestions.put(4017,new Questions("30 / 6", "6","5","6.6","5",chapter4));
        holdQuestions.put(4018,new Questions("23 / 11.5", "2.2","2.5","2","2",chapter4));
        holdQuestions.put(4019,new Questions("45.9 / 15.3  ", "3.3","3.2","3","3",chapter4));
        holdQuestions.put(4020,new Questions("36 / 12", "3","4","2.5","3",chapter4));
        holdQuestions.put(4021,new Questions("1000 / 20", "25","100","50","50",chapter4));
        holdQuestions.put(4022,new Questions("42 / 2", "21","20","22","21",chapter4));
        holdQuestions.put(4023,new Questions("360 / 120", "3","2","4","3",chapter4));

        holdQuestions.put(4024,new Questions("72 / 4", "14","18","16","18",chapter4));
        holdQuestions.put(4025,new Questions("144 / 12", "12","14","16","12",chapter4));
        holdQuestions.put(4026,new Questions("49 / 7", "6","7","8","7",chapter4));
        holdQuestions.put(4027,new Questions("81 / 9", "9","10","8","9",chapter4));
        holdQuestions.put(4028,new Questions("121 / 11", "8","9","10","11",chapter4));
        holdQuestions.put(4029,new Questions("110 / 11", "10.1","11","12","11",chapter4));
        holdQuestions.put(4030,new Questions("38.5 / 5.5", "5.1","6.1","7","7",chapter4));
        holdQuestions.put(4031,new Questions("556 / 2", "223","278","253","278",chapter4));

        //HARD CHAP 4 - DIVISION
        holdQuestions.put(4800,new Questions("3852 / 3", "1284","1264","1294","1284",hardChap4));
        holdQuestions.put(4801,new Questions("7926 / 6", "2641","2612","2642","2642",hardChap4));
        holdQuestions.put(4802,new Questions("9142 / 7", "1306","1316","1309","1306",hardChap4));
        holdQuestions.put(4803,new Questions("9144 / 8", "1153","1143","1133","1143",hardChap4));
        holdQuestions.put(4804,new Questions("7992 / 4", "1998","1992","1996","1998",hardChap4));
        holdQuestions.put(4805,new Questions("23,759 / 2", "11,879","11879.25","11,879.5","11,879.5",hardChap4));
        holdQuestions.put(4806,new Questions("12,927 / 3", "4303","4319","4309","4309",hardChap4));
        holdQuestions.put(4807,new Questions("1359 / 9", "163","154","151","151",hardChap4));
        holdQuestions.put(4808,new Questions("2628 / 9", "292","294","291","292",hardChap4));
        holdQuestions.put(4809,new Questions("16,985 / 5", "3397","3377","3387","3397",hardChap4));


        // CHAPTER 5 - EXPONENTS
        holdQuestions.put(5000,new Questions("8^2", "16","64","88","64",chapter5));
        holdQuestions.put(5001,new Questions("2^3", "8","24","6","8",chapter5));
        holdQuestions.put(5002,new Questions("10^4", "100,000","1000","10,000","10,000",chapter5));
        holdQuestions.put(5003,new Questions("5^2", "10","25","20","25",chapter5));
        holdQuestions.put(5004,new Questions("9^2", "76","18","81","81",chapter5));
        holdQuestions.put(5005,new Questions("2^4", "8","16","32","16",chapter5));
        holdQuestions.put(5006,new Questions("12^2", "144","24","90","144",chapter5));
        holdQuestions.put(5007,new Questions("4^3", "48","64","24","64",chapter5));

        holdQuestions.put(5008,new Questions("10^2", "110","1000","100","100",chapter5));
        holdQuestions.put(5009,new Questions("3^2", "9","12","6","9",chapter5));
        holdQuestions.put(5010,new Questions("4^2", "24","16","8","16",chapter5));
        holdQuestions.put(5011,new Questions("6^2", "36","64","30","36",chapter5));
        holdQuestions.put(5012,new Questions("7^2", "77","70","49","49",chapter5));
        holdQuestions.put(5013,new Questions("8^3", "512","88","240","512",chapter5));
        holdQuestions.put(5014,new Questions("9^3", "649","81","729","729",chapter5));
        holdQuestions.put(5015,new Questions("45^1", "45","90","2025","45",chapter5));

        holdQuestions.put(5016,new Questions("9^1", "18","81","9","9",chapter5));
        holdQuestions.put(5017,new Questions("10^5", "1000","10,000","100,000","100,000",chapter5));
        holdQuestions.put(5018,new Questions("13^2", "156","169","144","169",chapter5));
        holdQuestions.put(5019,new Questions("14^2", "196","178","190","196",chapter5));
        holdQuestions.put(5020,new Questions("3^3", "9","27","21","27",chapter5));
        holdQuestions.put(5021,new Questions("(-4)^3", "-64","64","-12","-64",chapter5));
        holdQuestions.put(5022,new Questions("2^5", "52","64","32","32",chapter5));
        holdQuestions.put(5023,new Questions("2^6", "64","32","48","64",chapter5));

        holdQuestions.put(5024,new Questions("(-2)^3", "-12","-8","8","-8",chapter5));
        holdQuestions.put(5025,new Questions("4^0", "4","1","0","1",chapter5));
        holdQuestions.put(5026,new Questions("9^0", "9","1","0","1",chapter5));
        holdQuestions.put(5027,new Questions("(-2)^2", "4","-4","8","4",chapter5));
        holdQuestions.put(5028,new Questions("(-3)^2", "-9","6","9","9",chapter5));
        holdQuestions.put(5029,new Questions("(-6)^2", "-12","36","-36","36",chapter5));
        holdQuestions.put(5030,new Questions("(-8)^0", "-8","-1","1","1",chapter5));
        holdQuestions.put(5031,new Questions("(-2)^4", "-16","16","-8","16",chapter5));

        //HARD CHAP 5 - EXPONENTS
        holdQuestions.put(5800,new Questions("4.4^2", "19.36","19.64","17.64","19.36",hardChap5));
        holdQuestions.put(5801,new Questions("4.1^2", "17.24","16.81","16.84","16.81",hardChap5));
        holdQuestions.put(5802,new Questions("2.6^2", "6.76","6.2","5.24","6.76",hardChap5));
        holdQuestions.put(5803,new Questions("9.7^2", "99.04","94.09","94.14","94.09",hardChap5));
        holdQuestions.put(5804,new Questions("1.8^2", "3.9","3.45","3.24","3.24",hardChap5));
        holdQuestions.put(5805,new Questions("1.3^2", "1.69","1.72","1.96","1.69",hardChap5));
        holdQuestions.put(5806,new Questions("5.9^2", "35.78","32.67","34.81","34.81",hardChap5));
        holdQuestions.put(5807,new Questions("6.3^2", "39.69","37.6","38.9","39.69",hardChap5));
        holdQuestions.put(5808,new Questions("7.7^2", "56.29","57.29","59.29","59.29",hardChap5));
        holdQuestions.put(5809,new Questions("3.4^2", "11.56","11.32","11.82","11.56",hardChap5));


        // CHAPTER 6 - RANDOM
        holdQuestions.put(6000,new Questions("12 + 43", "65","55","45","55",chapter6));
        holdQuestions.put(6001,new Questions("21 - 32", "-12","-13","-11","-11",chapter6));
        holdQuestions.put(6002,new Questions("20 + 287", "307","297","317","307",chapter6));
        holdQuestions.put(6003,new Questions("2^6", "32","64","24","64",chapter6));
        holdQuestions.put(6004,new Questions("12.3 - 12.87", "-0.57","-0.67","-0.47","-0.57",chapter6));
        holdQuestions.put(6005,new Questions("8.4 + 2.7", "10.1","11.1","11.3","11.1",chapter6));
        holdQuestions.put(6006,new Questions("-3.5 + 7.9", "4.4","5.3","3.4","4.4",chapter6));
        holdQuestions.put(6007,new Questions("-7.7 + 4.4", "-4.3","-3.3","-2.3","-3.3",chapter6));

        holdQuestions.put(6008,new Questions("32 / 2", "16","12","18","16",chapter6));
        holdQuestions.put(6009,new Questions("32 x 5", "190","180","160","160",chapter6));
        holdQuestions.put(6010,new Questions("10 x 10", "100","110","1000","100",chapter6));
        holdQuestions.put(6011,new Questions("120 / 5", "30","24","34","24",chapter6));
        holdQuestions.put(6012,new Questions("44 x 4", "180","172","176","176",chapter6));
        holdQuestions.put(6013,new Questions("13 / 0", "0","1","Undefined","Undefined",chapter6));
        holdQuestions.put(6014,new Questions("12 / 3", "4","3","6","4",chapter6));
        holdQuestions.put(6015,new Questions("12 x 12", "156","144","132","144",chapter6));

        holdQuestions.put(6016,new Questions("2 + 34 + 19", "54","55","56","55",chapter6));
        holdQuestions.put(6017,new Questions("32.45 + 31.69", "64.17","63.14","64.14","64.14",chapter6));
        holdQuestions.put(6018,new Questions("23 - 17.46", "5.54","6.64","4.64","5.54",chapter6));
        holdQuestions.put(6019,new Questions("20^2", "200","400","4000","400",chapter6));
        holdQuestions.put(6020,new Questions("21 / 3", "8","7","6","7",chapter6));
        holdQuestions.put(6021,new Questions("11 x 11", "110","111","121","121",chapter6));
        holdQuestions.put(6022,new Questions("15 / 3", "5","6","7","5",chapter6));
        holdQuestions.put(6023,new Questions("10 x 4.9", "49","490","49.9","49",chapter6));

        holdQuestions.put(6024,new Questions("32^0", "32","1","0","1",chapter6));
        holdQuestions.put(6025,new Questions("3^3", "64","9","27","27",chapter6));
        holdQuestions.put(6026,new Questions("2^1", "1","2","0","2",chapter6));
        holdQuestions.put(6027,new Questions("33 x 6", "199","165","198","198",chapter6));
        holdQuestions.put(6028,new Questions("12 + 89.3", "101.3","103.3","102.3","101.3",chapter6));
        holdQuestions.put(6029,new Questions("69 / 3", "13","23","33","23",chapter6));
        holdQuestions.put(6030,new Questions("78.4 + 43.8", "122.2","121.8","111.2","122.2",chapter6));
        holdQuestions.put(6031,new Questions("45 - 78", "-27","-23","-33","-33",chapter6));

        //HARD CHAP 6 - RANDOM
        holdQuestions.put(6800,new Questions("98.67 - 34.37", "64.1","64.6","64.3","64.3",hardChap6));
        holdQuestions.put(6801,new Questions("23.7 x 11", "237.7","260.7","284.4","260.7",hardChap6));
        holdQuestions.put(6802,new Questions("32.34 + 98.321 - 15.61", "115.051","114.051","114.011","115.051",hardChap6));
        holdQuestions.put(6803,new Questions("5 x 3^3", "125","135","145","135",hardChap6));
        holdQuestions.put(6804,new Questions("3.34 + 8.78 + 9.17", "21.29","20.29","21.19","21.29",hardChap6));
        holdQuestions.put(6805,new Questions("944,457 / 3", "314,816","311,623","314,819","314,819",hardChap6));
        holdQuestions.put(6806,new Questions("761,795 / 5", "152,341","152,359","152,353","152,359",hardChap6));
        holdQuestions.put(6807,new Questions("39.27 + 36.341 + 93.73", "167.431","169.341","169.431","169.341",hardChap6));
        holdQuestions.put(6808,new Questions("12.45 + 74.38 + 16.36", "103.19","102.29","103.39","103.19",hardChap6));
        holdQuestions.put(6809,new Questions("-46.45 - 32.94 - 39.98", "-118.27","-123.47","-119.37","-119.37",hardChap6));


        // CHAPTER 7 - ALGEBRA
        holdQuestions.put(7000,new Questions("12 + __ = 49", "37","39","36","37",chapter7));
        holdQuestions.put(7001,new Questions("150 / __ = 30", "4","5","6","5",chapter7));
        holdQuestions.put(7002,new Questions("__ - 13 = -2", "11","-11","-15","11",chapter7));
        holdQuestions.put(7003,new Questions("__ x 10 = 1200", "140","12","120","120",chapter7));
        holdQuestions.put(7004,new Questions("2^2 + __ = 35", "28","31","27","31",chapter7));
        holdQuestions.put(7005,new Questions("76 - 2^__ = 44", "5","6","4","5",chapter7));
        holdQuestions.put(7006,new Questions("450 + __ = 513", "53","63","73","63",chapter7));
        holdQuestions.put(7007,new Questions("90.9 / __ = 3.03", "30.3","30.1","30","30",chapter7));

        holdQuestions.put(7008,new Questions("89 x __ = 445", "4","6","5","5",chapter7));
        holdQuestions.put(7009,new Questions("12 x __ = 144", "12","11","13","12",chapter7));
        holdQuestions.put(7010,new Questions("121 / __ = 11", "10","11","9","11",chapter7));
        holdQuestions.put(7011,new Questions("12.3 + __ = 26.8", "13.5","12.6","14.5","14.5",chapter7));
        holdQuestions.put(7012,new Questions("13 + __ = 54", "41","43","51","41",chapter7));
        holdQuestions.put(7013,new Questions("65 - __ = -7.8", "-72.8","-73.8","-72.2","-72.8",chapter7));
        holdQuestions.put(7014,new Questions("30 * __ = 12O", "5","3","4","4",chapter7));
        holdQuestions.put(7015,new Questions("__ - 3^3 = 34.7", "67.7","61.7","57.7","61.7",chapter7));

        holdQuestions.put(7016,new Questions("__ x 10 = 100", "10.1","1","10","10",chapter7));
        holdQuestions.put(7017,new Questions("__ - 3.5 = 2.5", "6.5","5","5.5","5",chapter7));
        holdQuestions.put(7018,new Questions("__ - 32 = 43", "75","72","68","75",chapter7));
        holdQuestions.put(7019,new Questions("__ + 433 = 657", "213","214","224","224",chapter7));
        holdQuestions.put(7020,new Questions("(-4)^__ - 4.5 = -68.5", "2","4","3","3",chapter7));
        holdQuestions.put(7021,new Questions("__ - 33 = 17", "50","40","60","50",chapter7));
        holdQuestions.put(7022,new Questions("__ / 5 = 80", "400","800","300","400",chapter7));
        holdQuestions.put(7023,new Questions("__ + 45 = 134", "69","79","89","89",chapter7));

        holdQuestions.put(7024,new Questions("65 x __ = 260", "4","3","5","4",chapter7));
        holdQuestions.put(7025,new Questions("18 x __ = 54", "2","4","3","3",chapter7));
        holdQuestions.put(7026,new Questions("22 + __ = 56", "34","36","38","34",chapter7));
        holdQuestions.put(7027,new Questions("13 + __ = 432", "415","419","412","419",chapter7));
        holdQuestions.put(7028,new Questions("__ / 40 = 20", "2000","800","400","800",chapter7));
        holdQuestions.put(7029,new Questions("__ x 4 = 80", "25","15","20","20",chapter7));
        holdQuestions.put(7030,new Questions("675 - __ = 57", "608","618","628","618",chapter7));
        holdQuestions.put(7031,new Questions("90 - __ = 47", "43","33","53","43",chapter7));

        //HARD CHAP 7 - ALGEBRA
        holdQuestions.put(7800,new Questions("12.27 + __ = 321.89", "309.62","310.62","308.62","309.62",hardChap7));
        holdQuestions.put(7801,new Questions("34.89 - __ = -43.56", "78.65","78.56","78.45","78.45",hardChap7));
        holdQuestions.put(7802,new Questions("67.31 - __ = -27.23", "84.64","94.54","94.34","94.54",hardChap7));
        holdQuestions.put(7803,new Questions("__ x 30.2 = 453", "16","15","13","15",hardChap7));
        holdQuestions.put(7804,new Questions("__ / 40.5 = 32", "1296","1336.5","1255.5","1296",hardChap7));
        holdQuestions.put(7805,new Questions("__ / 35.7 = 10", "357.7","357","350.7","357",hardChap7));
        holdQuestions.put(7806,new Questions("3.5 + 2^4 - __ = 66 ", "-46.5","-44.5","45.5","-46.5",hardChap7));
        holdQuestions.put(7807,new Questions("2 x 4.5 + __ = 45.6", "36.6","35.6","34.6","36.6",hardChap7));
        holdQuestions.put(7808,new Questions("210.32 + 32.16 - __ = 212.34", "32.16","30.14","28.94","30.14",hardChap7));
        holdQuestions.put(7809,new Questions("32.76 - 43.12 + __ = 6.76", "18.12","17.12","15.26","17.12",hardChap7));

        // CHAPTER 8 - WORDED PROBLEMS
        holdQuestions.put(8000,new Questions("James has 12 marbles. Mary has two times his amount plus two. How many marbles do they have altogether?", "26","38","36","38",chapter8));
        holdQuestions.put(8001,new Questions("A dress cost $59.00. Trisha buys three dresses. She pays with $200.00 dollars. How much is her change?", "$23.00","$22.00","$21.00","$23.00",chapter8));
        holdQuestions.put(8002,new Questions("Khan is 60 years old. Dan is half Khan's age. Rebecca is thirteen years younger than Dan. Rena is twice Rebecca's age. How old is Rena?", "34","94","48","34",chapter8));
        //holdQuestions.put(8003,new Questions("Teresa's husband was five years older, died at age 41 when she was pregnant with her fourth child. Her forth child was 49 when Teresa died? Approximately, how old was Teresa when she died?", "","","","",chapter8));
        //holdQuestions.put(8004,new Questions("Danny was 18 years old when he starting working. He was fired and did not find a job for two years. He worked his second job until he was 63 then retired. ", "","","","",chapter8));
        holdQuestions.put(8003,new Questions("Jenny bought five boxes of pizzas. Each box contained 12 slices. How many slices of pizza was there altogether?", "60","72","12","60",chapter8));
        holdQuestions.put(8004,new Questions("Romero played 20 football matches and scored 39 goals overall. He scored 2 goals in each of his last 12 matches. How many did he score in his first 8 matches?", "15","13","17","15",chapter8));
        holdQuestions.put(8005,new Questions("Kenneth has five books. Jess has eleven books. Ronald has thirteen books. How many books altogether?", "29 books","28 books","30 books","29 books",chapter8));

        holdQuestions.put(8006,new Questions("There are 144 tools and 6 workers. Each worker must receive an equal share of the tools. How many tools will each worker attain?", "26 tools","28 tools","24 tools","24 tools",chapter8));
        holdQuestions.put(8007,new Questions("Jacob has seven unique clothing items. Lacey has triple the items? How many unique clothing items does Lacey have?", "21","28","14","21",chapter8));
        holdQuestions.put(8008,new Questions("Quincy's grocery bill was $54.47. How much money would Quincy have to pay to receive $26.53 in change?", "$80.00","$70.00","$78.00","$80.00",chapter8));
        holdQuestions.put(8009,new Questions("A can of milk costs $3.50. A bag of flour costs $12.00. Sammy bought 2 cans of milk and three bags of flour. What was his bill?", "$41.00","$39.00","$43.00","$41.00",chapter8));
        holdQuestions.put(8010,new Questions("A hammer costs $32.63. A wheelbarrow costs twice as much. How much does the wheelbarrow cost?", "$65.28","$65.26","$65.16","$65.26",chapter8));
        holdQuestions.put(8011,new Questions("A man flips a coin 195 times. Exactly a third of the time it lands on tails. How many times did the coin land on heads?", "140","65","130","130",chapter8));
        holdQuestions.put(8012,new Questions("Which one does NOT give the same portion?", "0.8, 80%, 4/5","0.8, 4/5, 16/20","4/5, 8%, 80/100","4/5, 8%, 80/100",chapter8));
        holdQuestions.put(8013,new Questions("Stacy ate 4/10 of the sponge cake. Jack ate 1/3 of the cake that was left. What fraction of cake was left? ", "2/5","3/10","3/5","2/5",chapter8));

        holdQuestions.put(8014,new Questions("Daniel drinks 500 millilitres of milk a day. How many litres of milk would he drink in 1 week?", "3.5 litres","3500 litres","2.8 litres","3.5 litres",chapter8));
        holdQuestions.put(8015,new Questions("Nathaniel bought 2 kilograms of gardening soil. How much soil will he spread evenly between 16 plants?", "200 Grams","150 Grams","125 Grams","125 Grams",chapter8));
        holdQuestions.put(8016,new Questions("Derrick buys four packs of tennis balls, each pack contains 6 balls. How many balls evenly shared will three friends get including Derrick?", "4 balls","8 balls","6 balls","6 balls",chapter8));
        holdQuestions.put(8017,new Questions("Pam runs 1.7 kilometres each day. After 5 days, how many kilometres did Pam run?", "8.5 km","7.5 km","9.2 km","8.5 km",chapter8));
        holdQuestions.put(8018,new Questions("A movie theatre has 15 rows with each row accommodating 11 seats. How many seats in total are there?", "165 seats","180 seats","175 seats","165 seats",chapter8));
        holdQuestions.put(8019,new Questions("Felicia and Sergio have 16 pieces of candy in total. Sergio has 7 pieces. How many pieces does Felicia have?", "8 pieces","9 pieces","11 pieces","9 pieces",chapter8));
        holdQuestions.put(8020,new Questions("Abigail earns $12.75 for each hour worked. How much money will she collect working for 5 hours?", "$95.00","$63.75","$73.75","$63.75",chapter8));
        holdQuestions.put(8021,new Questions("Nadia and Brian obtained 30 fruits added together. Brian obtained 13 fruits in that collection. How many more fruits does Nadia have? ", "17 fruits","7 fruits","27 fruits","17 fruits",chapter8));

        //HARD CHAP 8 - WORDED PROBLEMS
        holdQuestions.put(8822,new Questions("(Five cubed plus eighteen) minus thirty-five equals", "108","118","112","108",hardChap8));
        holdQuestions.put(8823,new Questions("Twelve squared multiply by eleven equals", "1594","1584","1356","1584",hardChap8));
        holdQuestions.put(8824,new Questions("(Negative nine) squared multiply by 3 equals", "263","243","253","243",hardChap8));
        holdQuestions.put(8825,new Questions("Eighty seven divided by three minus 5 cubed equals", "-66","-75","-96","-96",hardChap8));
        holdQuestions.put(8826,new Questions("Three hundred and twenty five point ninety-one minus fifty-one point forty-three equals", "274.48","289.92","272.48","274.48",hardChap8));
        holdQuestions.put(8827,new Questions("Ten point forty-nine multiply by four equals", "43.16","42.36","41.96","41.96",hardChap8));
        holdQuestions.put(8828,new Questions("A third of three hundred forty-eight plus one twenty point two equals", "136.2","146.2","936.2","136.2",hardChap8));
        holdQuestions.put(8829,new Questions("One twenty-five divided by five minus One thirty-six point thirty-six equals", "-107.36","-111.36","-92.64","-111.36",hardChap8));
        holdQuestions.put(8830,new Questions("Unknown multiply by three point twenty-five equals nineteen point five. What is the unknown?", "5.75","6","5.25","6",hardChap8));
        holdQuestions.put(8831,new Questions("Three thirteen minus unknown equals twenty point thirty-six. What is the unknown?", "292.64","296.64","294.64","",hardChap8));

    }

    private void arts_quiz() {

        //CHAPTER 1 - PAINTERS
        holdQuestions.put(1000, new Questions("Who painted Lisa La Gioconda(Mona Lisa)?", "Leonardo Da Vinci", "Raphael", "Michelangelo", "Leonardo Da Vinci", chapter1));
        holdQuestions.put(1001, new Questions("Who painted Guernica?", "Pablo Picasso", "Salvador Dalí", "Georges Braque", "Pablo Picasso", chapter1));
        holdQuestions.put(1002, new Questions("Who painted The Last Supper?", "Leonardo da Vinci", "Michelangelo", "Raphael", "Leonardo da Vinci", chapter1));
        holdQuestions.put(1003, new Questions("Who painted The Starry Night?", "Vincent Van Gogh", "Claude Monet", "Edouard Manet", "Vincent Van Gogh", chapter1));
        holdQuestions.put(1004, new Questions("Who painted The Scream?", "Franz Marc", "Edvard Munch", "Vincent Van Gogh", "Edvard Munch", chapter1));
        holdQuestions.put(1005, new Questions("Who painted The Kiss?", "Gustave Moreau", "Edvard Munch", "Gustav Klimt", "Gustav Klimt", chapter1));
        holdQuestions.put(1006, new Questions("Who painted Girl With a Pearl Earring?", "Johannes Vermeer", "Frans Hals", "Rembrandt", "Johannes Vermeer", chapter1));
        holdQuestions.put(1007, new Questions("Who painted The Birth of Venus?", "Raphael", "Sandro Botticelli", "Titian", "Sandro Botticelli", chapter1));

        holdQuestions.put(1008, new Questions("Who painted Creation of Adam?", "Michelangelo", "Raphael", "Leonardo Da Vinci", "Michelangelo", chapter1));
        holdQuestions.put(1009, new Questions("Who painted American Gothic?", "Henri Matisse", "Jackson Pollock", "Grant Wood", "Grant Wood", chapter1));
        holdQuestions.put(1010, new Questions("Who painted Las Meninas?", "Diego Velázquez", "Alonso Cano", "Francisco Ribalta", "Diego Velázquez", chapter1));
        holdQuestions.put(1011, new Questions("Who painted The Persistence of Memory?", "Frida Kahlo", "Salvador Dalí", "René Magritte", "Salvador Dalí", chapter1));
        holdQuestions.put(1012, new Questions("Who painted The Night Watch?", "Rembrandt", "Gerrit Dou", "Jan Steen", "Rembrandt", chapter1));
        holdQuestions.put(1013, new Questions("Who painted Les Demoiselles d’Avignon?", "Frida Kahlo", "Salvador Dalí", "Pablo Picasso", "Pablo Picasso", chapter1));
        holdQuestions.put(1014, new Questions("Who painted A Sunday Afternoon on the Island of La Grande Jatte?", "Georges Seurat", "Camille Pissarro", "Paul Signac", "Georges Seurat", chapter1));
        holdQuestions.put(1015, new Questions("Who painted The Arnolfini Portrait?", "Jan van Eyck", "Johannes Vermeer", "Rembrandt", "Jan van Eyck", chapter1));

        holdQuestions.put(1016, new Questions("Who painted Liberty Leading the People?", "Eugène Delacroix", "Gerrit Dou", "Francisco Ribalta", "Eugène Delacroix", chapter1));
        holdQuestions.put(1017, new Questions("Who painted The Raft of the Medusa?", "Raphael", "Jan van Eyck", "Théodore Géricault", "Théodore Géricault", chapter1));
        holdQuestions.put(1018, new Questions("Who painted Wanderer above the Sea of Fog?", "Caspar Friedrich", "Georges Seurat", "Rembrandt", "Caspar Friedrich", chapter1));
        holdQuestions.put(1019, new Questions("Who painted Nighthawks?", "Grant Wood", "Gustave Courbet", "Edward Hopper", "Edward Hopper", chapter1));
        holdQuestions.put(1020, new Questions("Who painted Christina's World?", "Andrew Wyeth", "Edward Hopper", "Henri Matisse", "Andrew Wyeth", chapter1));
        holdQuestions.put(1021, new Questions("Who painted Impression Sunrise?", "Edgar Degas", "Claude Monet", "Vincent Van Gogh", "Claude Monet", chapter1));
        holdQuestions.put(1022, new Questions("Who painted The Swing?", "Jean-Honoré Fragonard", "Francisco Goya", "François Boucher", "Jean-Honoré Fragonard", chapter1));
        holdQuestions.put(1023, new Questions("Who painted Napolean Crossing The Alps?", "Jan van Eyck", "Jacques-Louis David", "Rembrandt", "Jacques-Louis David", chapter1));

        holdQuestions.put(1024, new Questions("Who painted The Gleaners?", "Jean-Honoré Fragonard", "Jean-François Millet", "Caspar Friedrich", "Jean-François Millet", chapter1));
        holdQuestions.put(1025, new Questions("Who painted The Tower Of Babel?", "Hieronymus Bosch", "Pieter the Elder", "Lucas the Elder", "Pieter the Elder", chapter1));
        holdQuestions.put(1026, new Questions("Who painted The Ninth Wave?", "Abram Arkhipov", "Ivan Aivazovsky", "Alexander Golovin", "Ivan Aivazovsky", chapter1));
        holdQuestions.put(1027, new Questions("Who painted The Fall of the Damned?", "Raphael", "Peter Rubens", "Vincenzo Manenti", "Peter Rubens", chapter1));
        holdQuestions.put(1028, new Questions("Who painted Dante and Virgil in Hell?", "William-Adolphe Bouguereau", "Michelangelo", "Peter Rubens", "William-Adolphe Bouguereau", chapter1));
        holdQuestions.put(1029, new Questions("Who painted The Potato Eaters?", "Vincent Van Gogh", "Michelangelo", "Rembrandt", "Vincent Van Gogh", chapter1));
        holdQuestions.put(1030, new Questions("Who painted The Hay Wain?", "John Constable", "Gerrit Dou", "René Magritte", "John Constable", chapter1));
        holdQuestions.put(1031, new Questions("Who painted the Whistler's Mother?", "James Whistler", "Edgar Degas", "Caspar Friedrich", "James Whistler", chapter1));

        //HARD CHAP 1 - PAINTERS
        holdQuestions.put(1800, new Questions("Who painted Night Creeper?", "Hieronymus Bosch", "Zdzisław Beksiński", "", "Zdzisław Beksiński", hardChap1));
        holdQuestions.put(1801, new Questions("Who painted The Face of War?", "Claude Monet", "Salvador Dalí", "Pablo Picasso", "Salvador Dalí", hardChap1));
        holdQuestions.put(1802, new Questions("Who painted Head of an Idealist?", "Ken Currie", "Hieronymus Bosch", "Edvard Munch", "Ken Currie", hardChap1));
        holdQuestions.put(1803, new Questions("Who painted The Water Ghost?", "Alfred Kubin", "Zdzisław Beksiński", "Salvador Dalí", "Alfred Kubin", hardChap1));
        holdQuestions.put(1804, new Questions("Who painted Necronom IV?", "Alfred Kubin", "Hans Rudolf Giger", "Ken Currie", "Hans Rudolf Giger", hardChap1));
        holdQuestions.put(1805, new Questions("Who painted The Nightmare?", "Edvard Munch", "Zdzisław Beksiński", "Henry Fuseli", "Henry Fuseli", hardChap1));
        holdQuestions.put(1806, new Questions("Who painted Gallowgate Lard?", "Ken Currie", "Edvard Munch", "Hieronymus Bosch", "Ken Currie", hardChap1));
        holdQuestions.put(1807, new Questions("Who painted Lucifero?", "Henry Fuseli", "Francesco Scaramuzza", "Ken Currie", "Francesco Scaramuzza", hardChap1));
        holdQuestions.put(1808, new Questions("Who painted Anatomical Pieces?", "Claude Monet", "Theodore Gericault", "Pablo Picasso", "Theodore Gericault", hardChap1));
        holdQuestions.put(1809, new Questions("Who painted Grave Goods?", "Jeff Christensen", "Henry Fuseli", "Alfred Kubin", "Jeff Christensen", hardChap1));

        // CHAPTER 2 - WHO SCULPTED?
        holdQuestions.put(2000, new Questions("Who sculpted The Thinker?", "Henry Moore", "Auguste Rodin", "Umberto Boccioni", "Auguste Rodin", chapter2));
        holdQuestions.put(2001, new Questions("Who sculpted David around 1501 - 1504?", "Michelangelo", "Antonio Canova", "Donatello", "Michelangelo", chapter2));
        holdQuestions.put(2002, new Questions("Who sculpted Venus de Milo?", "Alexandros of Antioch", "Myron", "Phidias", "Alexandros of Antioch", chapter2));
        holdQuestions.put(2003, new Questions("Who sculpted The Pietà", "Donatello", "Raphael", "Michelangelo", "Michelangelo", chapter2));
        holdQuestions.put(2004, new Questions("Who sculpted Ecstasy of Saint Teresa?", "Gian Bernini", "Benvenuto Cellini", "Edgar Degas", "Gian Bernini", chapter2));
        holdQuestions.put(2005, new Questions("Who sculpted Perseus with the Head of Medusa?", "Jean-Antoine Houdon", "Bertel Thorvaldsen", "Antonio Canova", "Antonio Canova", chapter2));
        holdQuestions.put(2006, new Questions("Who sculpted The Little Fourteen-Year-Old Dancer?", "Constantin Brâncuși", "Edgar Degas", "Camille Claudel", "Edgar Degas", chapter2));
        holdQuestions.put(2007, new Questions("Who sculpted The Burghers of Calais?", "Constantin Brâncuși", "Auguste Rodin", "Edgar Degas", "Auguste Rodin", chapter2));

        holdQuestions.put(2008, new Questions("Who sculpted The Kiss sculpture in 1882?", "Gian Bernini", "Auguste Rodin", "Umberto Boccioni", "Auguste Rodin", chapter2));
        holdQuestions.put(2009, new Questions("Who sculpted Abraham Lincoln?", "Daniel French", "Alexander Calder", "Gutzon Borglum", "Daniel French", chapter2));
        holdQuestions.put(2010, new Questions("Who sculpted Unique Forms of Continuity in Space?", "Giacomo Manzù", "Marino Marini", "Umberto Boccioni", "Umberto Boccioni", chapter2));
        holdQuestions.put(2011, new Questions("Who sculpted Penitent Magdalene around 1453 – 1455?", "Donatello", "Caravaggio", "Titian", "Donatello", chapter2));
        holdQuestions.put(2012, new Questions("Who sculpted Pluto and Persephone?", "Gian Bernini", "Raphael", "Auguste Rodin", "Gian Bernini", chapter2));
        holdQuestions.put(2013, new Questions("Who sculpted Bacchus between 1496 - 1497?", "Michelangelo", "Donatello", "Raphael", "Michelangelo", chapter2));
        holdQuestions.put(2014, new Questions("Who sculpted Moses between 1513 and 1516?", "Caravaggio", "Damián Forment", "Michelangelo", "Michelangelo", chapter2));
        holdQuestions.put(2015, new Questions("Who sculpted Salt Cellar of Neptune and Ceres in 1543?", "Benvenuto Cellini", "Damián Forment", "Antonio Canova", "Benvenuto Cellini", chapter2));

        holdQuestions.put(2016, new Questions("Who sculpted St George Killing the Dragon around 1416?", "Donatello", "Benvenuto Cellini", "Veit Stoss", "Donatello", chapter2));
        holdQuestions.put(2017, new Questions("Who sculpted Feast of Herod?", "Donatello", "Michelangelo", "Peter Paul", "Donatello", chapter2));
        holdQuestions.put(2018, new Questions("Who sculpted St. Mark between 1411 -1413?", "Peter Paul", "Donatello", "Lorenzo Ghiberti", "Donatello", chapter2));
        holdQuestions.put(2019, new Questions("Who sculpted St John the Baptist?", "Lorenzo Ghiberti", "Michelangelo", "Donatello", "Lorenzo Ghiberti", chapter2));
        holdQuestions.put(2020, new Questions("Who sculpted St Matthew?", "Titian", "Donatello", "Michelangelo", "Michelangelo", chapter2));
        holdQuestions.put(2021, new Questions("Who sculpted The Gates of Paradise?", "André Beauneveu", "Lorenzo Ghiberti", "Filippo Brunelleschi", "Lorenzo Ghiberti", chapter2));
        holdQuestions.put(2022, new Questions("Who sculpted Neptune and Triton?", "Giacomo Manzù", "Auguste Rodin", "Gian Bernini", "Gian Bernini", chapter2));

        holdQuestions.put(2023, new Questions("Who sculpted Fountain of the Four Rivers?", "Gian Bernini", "Benvenuto Cellini", "Giacomo Manzù", "Gian Bernini", chapter2));
        holdQuestions.put(2024, new Questions("Who sculpted Cupid and Psyche?", "Antonio Canova", "Auguste Rodin", "Caravaggio", "Antonio Canova", chapter2));
        holdQuestions.put(2025, new Questions("Who sculpted Daedalus and Icarus in 1779?", "Etienne Falconet", "Joseph Nollekens", "Antonio Canova", "Antonio Canova", chapter2));
        holdQuestions.put(2026, new Questions("Who sculpted Theseus and the Minotaur?", "Gian Bernini", "Antonio Canova", "Auguste Rodin", "Antonio Canova", chapter2));
        holdQuestions.put(2027, new Questions("Who sculpted The Little Mermaid?", "Edvard Eriksen", "Aristide Maillol", "Ernst Barlach", "Edvard Eriksen", chapter2));
        holdQuestions.put(2028, new Questions("Who sculpted Manneken Pis?", "Jerôme Duquesnoy", "Michelangelo", "Donatello", "Jerôme Duquesnoy", chapter2));
        holdQuestions.put(2029, new Questions("Who sculpted Apollo & Daphne?", "Gian Bernini", "Antonio Canova", "Jerôme Duquesnoy", "Gian Bernini", chapter2));
        holdQuestions.put(2030, new Questions("Who sculpted Ecstasy of St Theresa?", "Caravaggio", "Michelangelo", "Gian Bernini", "Gian Bernini", chapter2));

        //HARD CHAP 2 - WHO SCULPTED?
        holdQuestions.put(2800, new Questions("Who sculpted Milo of Croton between 1671 – 1682?", "Auguste Rodin", "Pierre Puget", "Antonio Canova", "Pierre Puget", hardChap2));
        holdQuestions.put(2801, new Questions("Who sculpted The Deposition?", "Caravaggio", "Michelangelo", "Donatello", "Michelangelo", hardChap2));
        holdQuestions.put(2802, new Questions("Who sculpted Hercules and Cacus?", "Bartolommeo Bandinelli", "Auguste Rodin", "Gian Bernini", "Bartolommeo Bandinelli", hardChap2));
        holdQuestions.put(2803, new Questions("Who sculpted Balloon Dog?", "Robert Gober", "Paige Bradley", "Jeff Koons", "Jeff Koons", hardChap2));
        holdQuestions.put(2804, new Questions("Who sculpted Hiawatha?", "Auguste Rodin", "Augustus Saint-Gaudens", "Peter Paul", "Augustus Saint-Gaudens", hardChap2));
        holdQuestions.put(2805, new Questions("Who sculpted The Three Graces?", "Antonio Canova", "Antonio Corradini", "Michelangelo", "Antonio Canova", hardChap2));
        holdQuestions.put(2806, new Questions("Who sculpted Le Génie du Mal?", "Guillaume Geefs", "Benvenuto Cellini", "Etienne Falconet", "Guillaume Geefs", hardChap2));
        holdQuestions.put(2807, new Questions("Who sculpted Madonna of Bruges?", "Donatello", "Michelangelo", "Caravaggio", "Michelangelo", hardChap2));
        holdQuestions.put(2808, new Questions("Who sculpted Nuclear energy?", "Frederic Remington", "Aristide Maillol", "Henry Moore", "Henry Moore", hardChap2));
        holdQuestions.put(2809, new Questions("Who sculpted Veiled Vestal in 1743?", "Gian Bernini", "Antonio Canova", "Antonio Corradini", "Antonio Corradini", hardChap2));


        // CHAPTER 3 - ART TERMS
        holdQuestions.put(3000, new Questions("What is hue in art?", "name of a color", "brightness of a color", "dark colors", "name of a color", chapter3));
        holdQuestions.put(3001, new Questions("What art term is defined as \"light values of a color (adding white)\"?", "tint", "unity", "texture", "tint", chapter3));
        holdQuestions.put(3002, new Questions("What art term is defined as \"dark values of a color (adding black).\"?", "tint", "shade", "hue", "shade", chapter3));
        holdQuestions.put(3003, new Questions("What word is used to describe the brightness of a color?", "Intensity", "Exaggeration", "Value", "Intensity", chapter3));
        holdQuestions.put(3004, new Questions("Warm colors include Red, Yellow and...", "Orange", "Green", "Blue", "Orange", chapter3));
        holdQuestions.put(3005, new Questions("Cool colors include Blue, Green and...", "Violet", "Red", "Orange", "Violet", chapter3));
        holdQuestions.put(3006, new Questions("A line is a mark or implied mark between....", "two endpoints", "an endpoint", "three endpoints", "two endpoints", chapter3));
        holdQuestions.put(3007, new Questions("What is the meaning of the word pastiche?", "copy", "write", "paste", "copy", chapter3));

        holdQuestions.put(3008, new Questions("Visual art form assemblage represents", "3D collage", "collage", "collection", "3D collage", chapter3));
        holdQuestions.put(3009, new Questions("What is Monochromatic?", "One color", "One colored pencil", "Two colors", "One color", chapter3));
        holdQuestions.put(3010, new Questions("Contour means...", "a line", "a shape", "an edge", "a line", chapter3));
        holdQuestions.put(3011, new Questions("Which is impasto?", "thick", "thin", "medium thin", "thick", chapter3));
        holdQuestions.put(3012, new Questions("Calligraphy is the art of...", "handwriting", "pasting", "painting", "handwriting", chapter3));
        holdQuestions.put(3013, new Questions("Art created by assembling a variety of forms such as paper and photography is called...", "Collage", "Collection", "Codex", "Collage", chapter3));
        holdQuestions.put(3014, new Questions("The elements which make up a piece of art\"(The color, the shape, the size, the medium etc.\" is called...", "Form", "Emboss", "Theme", "Form", chapter3));
        holdQuestions.put(3015, new Questions("Which represents an oft-repeated feature?", "Motif", "Mandala", "Mural", "Motif", chapter3));

        holdQuestions.put(3016, new Questions("A drawing technique where numerous dots construct the image.", "Stilling", "Surrealism", "Stippling", "Stippling", chapter3));
        holdQuestions.put(3017, new Questions("A large picture painted directly on to a wall or ceiling, often in fresco.", "Mural", "Muse", "Motif", "Mural", chapter3));
        holdQuestions.put(3018, new Questions("A printing method using copper engraving.", "Intaglio", "Mezzotint", "Lithography", "Mezzotint", chapter3));
        holdQuestions.put(3019, new Questions("A particular shade of color.", "value", "hue", "tint", "hue", chapter3));
        holdQuestions.put(3020, new Questions("What is hatching?", "drawing technique ", "painting standard", "type of model", "drawing technique", chapter3));
        holdQuestions.put(3021, new Questions("To request a work of art to be produced.", "Commission", "Composition", "Constructivism", "Commission", chapter3));

        holdQuestions.put(3022, new Questions("The topic of interest or the primary theme of an artwork.", "Medium", "Motif", "Subject matter", "Subject matter", chapter3));
        holdQuestions.put(3023, new Questions("The smooth transition from one colour (or value of a colour) to another.", "Gradation", "Bleeding", "Pochade", "Gradation", chapter3));
        holdQuestions.put(3024, new Questions("The overall value of a drawing.", "Key", "Tone", "Type", "Key", chapter3));
        holdQuestions.put(3025, new Questions("The part of a drawing or painting between the foreground and background.", "Middle ground", "Focus point", "Point of interest", "Middle ground", chapter3));
        holdQuestions.put(3026, new Questions("A traditional type of artwork meant to remind the viewer of their own mortality.", "Memento Mori", "divisionism", "anamorphosis", "Memento Mori", chapter3));

        //HARD CHAP 3 - ART TERMS
        holdQuestions.put(3800, new Questions("Creating a pattern of many small dots that will blend into a shape when viewed from a distance.", "Hatching", "Stippling", "Dotting", "Stippling", hardChap3));
        holdQuestions.put(3801, new Questions("The level of lightness/darkness of a drawing or painting.", "Tone", "Tint", "Shade", "Tone", hardChap3));
        holdQuestions.put(3802, new Questions("What art term represents the use of strong contrasts between light and dark?", "Fresco", "Sfumato", "Chiaroscuro", "Chiaroscuro", hardChap3));
        holdQuestions.put(3803, new Questions("Gradually merging two colors to create a gradient.", "Bleeding", "Blending", "Ébauche", "Blending", hardChap3));
        holdQuestions.put(3804, new Questions("The arrangement of forms in a work of art.", "Composition", "Commission", "Focal point", "Composition", hardChap3));


        // CHAPTER 4 - RENAISSANCE ARTISTS
        holdQuestions.put(4000, new Questions("________ da Vinci", "Leonardo", "Claudio", "Cortez", "Leonardo", chapter4));
        holdQuestions.put(4001, new Questions("________ Botticelli", "Sandro", "Sandy", "Mariano", "Sandro", chapter4));
        holdQuestions.put(4002, new Questions("________ Vasari", "Giorgio", "Antonio", "Ricardo", "Giorgio", chapter4));
        holdQuestions.put(4003, new Questions("________ Lippi", "Filippino", "Sandro", "Antonio", "Filippino", chapter4));
        holdQuestions.put(4004, new Questions("________ Bellini", "Raphael", "Gentile", "Titian", "Gentile", chapter4));
        holdQuestions.put(4005, new Questions("________ Bellini", "Leonardo", "Michelangelo", "Giovanni", "Giovanni", chapter4));
        holdQuestions.put(4006, new Questions("________ Fouquet", "Michelangelo", "Antonio", "Jean", "Jean", chapter4));
        holdQuestions.put(4007, new Questions("________ Bosch", "Hieronymus", "Leonardo", "Cortez", "Hieronymus", chapter4));

        holdQuestions.put(4008, new Questions("________ Cellini", "Giorgio", "Raphael", "Benvenuto", "Benvenuto", chapter4));
        holdQuestions.put(4009, new Questions("________ del Sarto", "Andrea", "Mariano", "Titian", "Andrea", chapter4));
        holdQuestions.put(4010, new Questions("________ da Messina", "Hieronymus", "Antonello", "Gentile", "Antonello", chapter4));
        holdQuestions.put(4011, new Questions("________ di Banco", "Nanni", "Sandro", "Titian", "Nanni", chapter4));


        holdQuestions.put(4012, new Questions("________ Dürer", "Sandy", "Albrecht", "Ricardo", "Albrecht", chapter4));
        holdQuestions.put(4013, new Questions("________ Mantegna", "Andrea", "Claudio", "Ricardo", "Andrea", chapter4));
        holdQuestions.put(4014, new Questions("________ Sanzio", "Claudio", "Raffaello", "Hieronymus", "Raffaello", chapter4));
        holdQuestions.put(4015, new Questions("________ Perugino", "Sandy", "Pietro", "Giovanni", "Pietro", chapter4));
        holdQuestions.put(4016, new Questions("________ Vecelli", "Tiziano", "Jean", "Giovanni", "Tiziano", chapter4));
        holdQuestions.put(4017, new Questions("________ Simoni", "Nanni", "Michelangelo", "Leonardo", "Michelangelo", chapter4));
        holdQuestions.put(4018, new Questions("________ Bardi", "Donato", "Nanni", "Fra", "Donato", chapter4));
        holdQuestions.put(4019, new Questions("________ van Hemessen", "Catharina", "Masolino", "Filippino", "Catharina", chapter4));

        //HARD CHAP 5 - RENAISSANCE ARTISTS
        holdQuestions.put(4800, new Questions("________ da Panicale", "Leonardo", "Antonio", "Masolino", "Masolino", hardChap4));
        holdQuestions.put(4801, new Questions("________ Angelico", "Antonio", "Fra", "Filippino", "Fra", hardChap4));
        holdQuestions.put(4802, new Questions("________ Ghiberti", "Lorenzo", "Antonio", "Cortez", "Lorenzo", hardChap4));
        holdQuestions.put(4803, new Questions("________ van Eyck", "Raphael", "Cortez", "Jan", "Jan", hardChap4));


        // CHAPTER 5 - BAROQUE ARTISTS
        holdQuestions.put(5000, new Questions("________ Vermeer", "Bartolomé", "Johannes", "Manoel", "Johannes", chapter5));
        holdQuestions.put(5001, new Questions("________ Paul Rubens", "Peter", "Jusepe", "Annibale", "Peter", chapter5));
        holdQuestions.put(5002, new Questions("________ Velázquez", "Pietro", "Georges", "Diego", "Diego", chapter5));
        holdQuestions.put(5003, new Questions("________ da Costa Ataíde", "Manoel", "Frans", "Georges", "Manoel", chapter5));
        holdQuestions.put(5004, new Questions("________ Gentileschi", "", "Artemisia", "Philippe", "Artemisia", chapter5));
        holdQuestions.put(5005, new Questions("________ Carracci", "Annibale", "Alonso", "Giovanni", "Annibale", chapter5));
        holdQuestions.put(5006, new Questions("________ van Dyck", "Elisabetta", "Anthony", "Georges", "Anthony", chapter5));
        holdQuestions.put(5007, new Questions("________ Esteban Murillo", "Bartolomé", "Pieter", "Frans", "Bartolomé", chapter5));

        holdQuestions.put(5008, new Questions("________ de Ribera", "Johannes", "Jusepe", "Philippe", "Jusepe", chapter5));
        holdQuestions.put(5009, new Questions("________ Reni", "Masolino", "Manoel", "Guido", "Guido", chapter5));
        holdQuestions.put(5010, new Questions("________ de Zurbarán", "Francisco", "Claude", "Pieter", "Francisco", chapter5));
        holdQuestions.put(5011, new Questions("________ da Cortona", "Anthony", "Pietro", "Pieter", "Pietro", chapter5));
        holdQuestions.put(5012, new Questions("________ Hals", "Frans", "Alonso", "Elisabetta", "Frans", chapter5));
        holdQuestions.put(5013, new Questions("________ Lorrain", "Salvator", "Claude", "Peter", "Claude", chapter5));
        holdQuestions.put(5014, new Questions("________ de La Tour", "Luca", "Georges", "Peter", "Georges", chapter5));
        holdQuestions.put(5015, new Questions("________ Cano", "Alonso", "Luca", "Diego", "Alonso", chapter5));

        holdQuestions.put(5016, new Questions("________ Barbieri", "Nanni", "Donato", "Giovanni", "Giovanni", chapter5));
        holdQuestions.put(5017, new Questions("________ Rosa", "Salvator", "Manoel", "Anthony", "Salvator", chapter5));
        holdQuestions.put(5018, new Questions("________ de Champaigne", "Frans", "Philippe", "Anthony", "Philippe", chapter5));
        holdQuestions.put(5019, new Questions("________ Giordano", "Luca", "Claude", "Jusepe", "Luca", chapter5));

        //HARD CHAP 5 - BAROQUE ARTISTS
        holdQuestions.put(5800, new Questions("________ Claesz", "Philippe", "Pieter", "Jusepe", "Pieter", hardChap5));
        holdQuestions.put(5801, new Questions("________ Callot", "Jacques", "Salvator", "Pieter", "Jacques", hardChap5));
        holdQuestions.put(5802, new Questions("________ Sirani", "Elisabetta", "Frans", "Francisco", "Elisabetta", hardChap5));
        holdQuestions.put(5803, new Questions("________ Jordaens", "Salvator", "Jacob", "Claude", "Jacob", hardChap5));


        // CHAPTER 6  - MOVIE VILLAINS
        holdQuestions.put(6000, new Questions("Who is the antagonist of No Country For Old Men?", "Anton Chigurh", "Amon Göth", "Micheal Myers", "Anton Chigurh", chapter6));
        holdQuestions.put(6001, new Questions("Which animal is portrayed as the villain in the movie Jaws?", "Shark", "Tiger", "Lion", "Shark", chapter6));
        holdQuestions.put(6002, new Questions("What is the name of the owner of the motel in Alfred Hitchcock's movie Psycho?", "Norman Bates", "James Fleck", "Arthur Curry", "Norman Bates", chapter6));
        holdQuestions.put(6003, new Questions("Who was the main villain in the original Star Wars trilogy?", "Darth Vader", "Darth Sidious", "Darth Maul", "Darth Vader", chapter6));
        holdQuestions.put(6004, new Questions("In Stephen Speilberg's Schlinders List actor Ralph Finnese portrays which real life Nazi villain?", "Amon Göth", "Adolf Hitler", "Karl Dönitz", "Amon Göth", chapter6));
        holdQuestions.put(6005, new Questions("Which villain appears in the movie Halloween?", "Micheal Myers", "Jason", "Freddy Krueger", "Micheal Myers", chapter6));
        holdQuestions.put(6006, new Questions("Which A.I. agent is the antagonist in the Matrix?", "Agent Dennet", "Agent Smith", "Agent Micheal", "Agent Smith", chapter6));
        holdQuestions.put(6007, new Questions("In lord of the rings, the dark lord of mordor is known by the name...", "Thanos", "Sauron", "Sidious", "Sauron", chapter6));

        holdQuestions.put(6008, new Questions("Which villain haunts and kills persons in their dreams Nightmare on Elm Street?", "Freddy Krueger", "Jason", "Micheal Myers", "Freddy Krueger", chapter6));
        holdQuestions.put(6009, new Questions("Who is the antagonist in Die Hard?", "Hans Gruber", "Hans Landa", "Hans Jacob", "Hans Gruber", chapter6));
        holdQuestions.put(6010, new Questions("Denzel Washington plays which corrupted detective in Training Day?", "Alonzo Harris", "Frank Booth", "Hans Gruber", "Alonzo Harris", chapter6));
        holdQuestions.put(6011, new Questions("Which disturbed and demented villain appears in the psycho horror movie Blue Velvet?", "Frank Booth", "Stanley Hitcher", "Ray Palmer", "Frank Booth", chapter6));
        holdQuestions.put(6012, new Questions("Who is the painted clown face villain in the Dark Knight?", "Joker", "Riddler", "Harley Quinn", "Joker", chapter6));
        holdQuestions.put(6013, new Questions("Which purple alien seeks to destroy half of existence in the Avengers?", "Thanos", "Darkseid", "Roman", "Thanos", chapter6));
        holdQuestions.put(6014, new Questions("In the Man of Steel movie Superman kills the villain...", "Zod", "Brainac", "Metallo", "Zod", chapter6));
        holdQuestions.put(6015, new Questions("In the Night of the Hunter movie actor Robert Mitchmum plays which murdering priest?", "Harry Powell", "David Clint", "Joesph Campbell", "Harry Powell", chapter6));

        holdQuestions.put(6016, new Questions("The villain of Chinatown is...", "Noah Cross", "Noah Daniels", "Noah Harper", "Noah Cross", chapter6));
        holdQuestions.put(6017, new Questions("What is the name of the creature that appears in Alien 1979 movie?", "Xenomorph", "Deacon", "Newborn", "Xenomorph", chapter6));
        holdQuestions.put(6018, new Questions("Which Terminator is the villain in Terminator 2: Judgement Day?", "T-1000", "T-800", "T-2000", "T-1000", chapter6));
        holdQuestions.put(6019, new Questions("Who is the main nazi villain portrayed in Inglorius Basterds?", "Hans Landa", "Adolf Hitler", "Amon Göth", "Hans Landa", chapter6));
        holdQuestions.put(6020, new Questions("Which alien monster sometimes personifies itself in the form of a clown in Stephen Kings It? ", "Pennywise", "Joker", "Clown Man", "Pennywise", chapter6));
        holdQuestions.put(6021, new Questions("Who is the female psychopath villain in Gone Girl?", "Amy Dunne", "Alex Forrest", "Annie Wilkes", "Amy Dunne", chapter6));
        holdQuestions.put(6022, new Questions("Who is the main antagonist in Django Unchained?", "Calvin Candie", "William Ford", "Edwin Epps", "Calvin Candie", chapter6));
        holdQuestions.put(6023, new Questions("Friday the 13th villain?", "Jason Kruegar", "Freddy Krueger", "Jason Voorhees", "Jason Voorhees", chapter6));

        //HARD CHAP 6 - MOVIE VILLAINS
        holdQuestions.put(6800, new Questions("Which is the computer villain in Kubrick's 2001: A Space Odyssey?", "HAL 5000", "HAL 4000", "HAL 9000", "HAL 9000", hardChap6));
        holdQuestions.put(6801, new Questions("Who is the villain in Franz Lang M?", "Hans Beckert", "Hans Gruber", "Hans Lang", "Hans Beckert", hardChap6));
        holdQuestions.put(6802, new Questions("American Psycho villain?", "Patrick Batman", "Patrick Bateman", "Patrick Baker", "Patrick Bateman", hardChap6));
        holdQuestions.put(6803, new Questions("Who is the villain in Oldboy 2003?", "Woo-Jin Lee", "Ji-Tae Yu", "Park Chan-wook", "Woo-Jin Lee", hardChap6));
        holdQuestions.put(6804, new Questions("Cape Fear villain?", "Max Cady", "Max Ford", "Max Cage", "Max Cady", hardChap6));
        holdQuestions.put(6805, new Questions("Who is the villain in 101 Dalmations?", "Yzma", "Lady Tremaine", "Cruella De Vil", "Cruella De Vil", hardChap6));
        holdQuestions.put(6806, new Questions("The Third Man villain?", "Harry Powell", "Harry Lime", "Harry Osborn", "Harry Lime", hardChap6));
        holdQuestions.put(6807, new Questions("Who is the villain in Pan's Labyrinth?", "Captain Cortez", "Captain Vidal", "Captain Salazar", "Captain Vidal", hardChap6));
        holdQuestions.put(6808, new Questions("All About Eve movie villain?", "Eve Connor", "Eve Forward", "Eve Harrington", "Eve Harrington", hardChap6));
        holdQuestions.put(6809, new Questions("Nosferatu movie villain?", "Graf Orlok", "Count Olaf", "Count Batula", "Graf Orlok", hardChap6));


        // CHAPTER 7 - MOVIES DIRECTORS
        holdQuestions.put(7000, new Questions("Who directed No Country For Old Men?", "Coen Brothers", "Steven Spielberg", "P.T. Anderson", "Coen Brothers", chapter7));
        holdQuestions.put(7001, new Questions("Who directed the horror film The Shining in 1980?", "Wes Craven", "Steven Spielberg", "Stanley Kubrick", "Stanley Kubrick", chapter7));
        holdQuestions.put(7002, new Questions("Who directed Inception?", "Christopher Nolan", "Denis Villeneuve", "James Cameron", "Christopher Nolan", chapter7));
        holdQuestions.put(7003, new Questions("Who directed Back to the Future?", "Richard Donner", "Steven Spielberg", "Robert Zemeckis", "Robert Zemeckis", chapter7));
        holdQuestions.put(7004, new Questions("Who directed There will be Blood?", "Coen Brothers", "P.T. Anderson", "Quentin Tarantino", "P.T. Anderson", chapter7));
        holdQuestions.put(7005, new Questions("Who directed Superman 1975?", "Richard Donner", "Robert Zemeckis", "Steven Spielberg", "Richard Donner", chapter7));
        holdQuestions.put(7006, new Questions("Who directed The Godfather?", "Brian de Palma", "Francis Coppola", "Robert Altman", "Francis Coppola", chapter7));
        holdQuestions.put(7007, new Questions("Who directed Joker?", "Todd Phillips", "Tim Burton", "Martin Scorsese", "Todd Phillips", chapter7));

        holdQuestions.put(7008, new Questions("Who directed Avengers Infinity War?", "Russo Brothers", "James Gunn", "Edgar Wright", "Russo Brothers", chapter7));
        holdQuestions.put(7009, new Questions("Who directed The Thing?", "John Carpenter", "Ridley Scott", "Wes Craven", "John Carpenter", chapter7));
        holdQuestions.put(7010, new Questions("Who directed Alien?", "James Cameron", "Ridley Scott", "Wes Craven", "Ridley Scott", chapter7));
        holdQuestions.put(7011, new Questions("Who directed Blade Runner?", "Ridley Scott", "David Cronenberg", "Steven Lisberger", "Ridley Scott", chapter7));
        holdQuestions.put(7012, new Questions("Who directed The Man Who Wasn't There?", "Mike Leigh", "Coen Brothers", "Clint Eastwood", "Coen Brothers", chapter7));
        holdQuestions.put(7013, new Questions("Who directed First Reformed?", "Martin Scorsese", "Paul Schrader", "Francis Coppola", "Paul Schrader", chapter7));
        holdQuestions.put(7014, new Questions("Who directed Taxi Driver?", "Martin Scorsese", "Sidney Lumet", "Robert Altman", "Martin Scorsese", chapter7));
        holdQuestions.put(7015, new Questions("Who directed Apocalypse Now?", "Martin Scorsese", "George Lucas", "Francis Coppola", "Francis Coppola", chapter7));

        holdQuestions.put(7016, new Questions("Who directed The Shawshank Redemption?", "Frank Darabont", "Robert Zemeckis", "Rob Reiner", "Frank Darabont", chapter7));
        holdQuestions.put(7017, new Questions("Who directed The Matrix?", "Terry Gilliam", "The Wachowskis'", "Alex Proyas", "The Wachowskis'", chapter7));
        holdQuestions.put(7018, new Questions("Who directed the psycho horror film Angel Heart?", "John Boorman", "Brian De Parma", "Alan Parker", "Alan Parker", chapter7));
        holdQuestions.put(7019, new Questions("Who directed the noir detective film Seven?", "David Fincher", "Darren Aronofsky", "Quentin Tarantino", "David Fincher", chapter7));
        holdQuestions.put(7020, new Questions("Who directed the Japanese psycho thriller The Cure?", "Kiyoshi Kurosawa", "Akira Kurosawa", "Hayao Miyazaki", "Kiyoshi Kurosawa", chapter7));
        holdQuestions.put(7021, new Questions("Who directed Rear Window?", "David Lynch", "Alfred Hitchcock", "Roman Polanski", "Alfred Hitchcock", chapter7));
        holdQuestions.put(7022, new Questions("Who directed Psycho?", "Alfred Hitchcock", "George Romero", "John Carpenter", "Alfred Hitchcock", chapter7));
        holdQuestions.put(7023, new Questions("Who directed Blue Velvet?", "David Lynch", "Alfred Hitchcock", "Martin Scorsese", "David Lynch", chapter7));

        holdQuestions.put(7024, new Questions("Who directed Barry Lyndon?", "Steven Spielberg", "Roman Polanski", "Stanley Kubrick", "Stanley Kubrick", chapter7));
        holdQuestions.put(7025, new Questions("Who directed Chinatown?", "Roman Polanski", "Stanley Kubrick", "", "Roman Polanski", chapter7));
        holdQuestions.put(7026, new Questions("Who directed The Fly?", "David Cronenberg", "John Carpenter", "Wes Craven", "David Cronenberg", chapter7));
        holdQuestions.put(7027, new Questions("Who directed The Dark Knight?", "David Fincher", "Christopher Nolan", "Darren Aronofsky", "Christopher Nolan", chapter7));
        holdQuestions.put(7028, new Questions("Who directed Pan's Labyrinth?", "George Romero", "Steven Spielberg", "Guillermo del Toro", "Guillermo del Toro", chapter7));
        holdQuestions.put(7029, new Questions("Who directed Man of Steel?", "Zack Snyder", "Christopher Nolan", "James Gunn", "Zack Snyder", chapter7));
        holdQuestions.put(7030, new Questions("Who directed Melancholia?", "Lars Von Trier", "Christopher Nolan", "Guillermo del Toro", "Lars Von Trier", chapter7));
        holdQuestions.put(7031, new Questions("Who directed Dark City?", "Alex Proyas", "David Fincher", "The Wachowskis'", "Alex Proyas", chapter7));

        //HARD CHAP 7 - MOVIE VILLAINS
        holdQuestions.put(7800, new Questions("Who directed Magnolia released 1999?", "P.T Anderson", "Darren Aronofsky", "Steven Spielberg", "P.T Anderson", hardChap7));
        holdQuestions.put(7801, new Questions("Who directed THX 1138?", "George Lucas", "Francis Coppola", "George Romero", "George Lucas", hardChap7));
        holdQuestions.put(7802, new Questions("Who directed Heat?", "Quentin Tarantino", "Roman Polanski", "Michael Mann", "Michael Mann", hardChap7));
        holdQuestions.put(7803, new Questions("Who directed Apocalypto?", "Mel Gibson", "James Cameroon", "Roland Emmerich", "Mel Gibson", hardChap7));
        holdQuestions.put(7804, new Questions("Who directed Let Me In?", "David Fincher", "Matt Reeves", "James Abrams", "Matt Reeves", hardChap7));
        holdQuestions.put(7805, new Questions("Who directed the dark comedy After Hours?", "Martin Scorsese", "Richard Donner", "Ivan Reitman", "Martin Scorsese", hardChap7));
        holdQuestions.put(7806, new Questions("Who directed Deep Red?", "Kubrick", "Hitchcock", "Dario Argento", "Dario Argento", hardChap7));
        holdQuestions.put(7807, new Questions("Who directed The Green Knight?", "David Lowrey", "Matt Reeves", "James Cameroon", "David Lowrey", hardChap7));
        holdQuestions.put(7808, new Questions("Who directed the horror movie The Wailing?", "Jang Young-gyu", "Na Hong-jin", "Choi Yong-rak", "Na Hong-jin", hardChap7));
        holdQuestions.put(7809, new Questions("Who directed PI?", "Christopher Nolan", "David Fincher", "Darren Aronofsky", "Darren Aronofsky", hardChap7));


        //CHAPTER 8 - RANDOM
        holdQuestions.put(8000, new Questions("Who directed the survivalist film Deliverance?", "John Boorman", "David Lynch", "Robert Altman", "John Boorman", chapter8));
        holdQuestions.put(8001, new Questions("Who directed The Fly?", "David Cronenberg", "John Carpenter", "Wes Craven", "David Cronenberg", chapter8));
        holdQuestions.put(8002, new Questions("Who directed The Dark Knight?", "David Fincher", "Christopher Nolan", "Darren Aronofsky", "Christopher Nolan", chapter8));
        holdQuestions.put(8003, new Questions("Who is the main nazi villain portrayed in Inglorius Basterds?", "Hans Landa", "Adolf Hitler", "Amon Göth", "Hans Landa", chapter8));
        holdQuestions.put(8004, new Questions("Which alien monster sometimes personifies itself in the form of a clown in Stephen Kings It? ", "Pennywise", "Joker", "Clown Man", "Pennywise", chapter8));
        holdQuestions.put(8005, new Questions("Which villain appears in the movie Halloween?", "Micheal Myers", "Jason", "Freddy Krueger", "Micheal Myers", chapter8));
        holdQuestions.put(8006, new Questions("Which A.I. agent is the antagonist in the Matrix?", "Agent Dennet", "Agent Smith", "Agent Micheal", "Agent Smith", chapter8));
        holdQuestions.put(8007, new Questions("In lord of the rings, the dark lord of mordor is known by the name...", "Thanos", "Sauron", "Sidious", "Sauron", chapter8));

        holdQuestions.put(8008, new Questions("________ Carracci", "Annibale", "Alonso", "Giovanni", "Annibale", chapter8));
        holdQuestions.put(8009, new Questions("________ van Dyck", "Elisabetta", "Anthony", "Georges", "Anthony", chapter8));
        holdQuestions.put(8010, new Questions("________ Esteban Murillo", "Bartolomé", "Pieter", "Frans", "Bartolomé", chapter8));
        holdQuestions.put(8011, new Questions("The overall value of a drawing.", "Key", "Tone", "Type", "Key", chapter8));
        holdQuestions.put(8012, new Questions("Creating a pattern of many small dots that will blend into a shape when viewed from a distance.", "Hatching", "Stippling", "Dotting", "Stippling", chapter8));
        holdQuestions.put(8013, new Questions("The level of lightness/darkness of a drawing or painting.", "Tone", "Tint", "Shade", "Tone", chapter8));
        holdQuestions.put(8014, new Questions("The part of a drawing or painting between the foreground and background.", "Middle ground", "Focus point", "Point of interest", "Middle ground", chapter8));
        holdQuestions.put(8015, new Questions("Who sculpted Feast of Herod?", "Donatello", "Michelangelo", "Peter Paul", "Donatello", chapter8));

        holdQuestions.put(8016, new Questions("Who sculpted St. Mark between 1411 -1413?", "Peter Paul", "Donatello", "Lorenzo Ghiberti", "Donatello", chapter8));
        holdQuestions.put(8017, new Questions("Who sculpted St John the Baptist?", "Lorenzo Ghiberti", "Michelangelo", "Donatello", "Lorenzo Ghiberti", chapter8));
        holdQuestions.put(8018, new Questions("Who painted Las Meninas?", "Diego Velázquez", "Alonso Cano", "Francisco Ribalta", "Diego Velázquez", chapter8));
        holdQuestions.put(8019, new Questions("Who painted The Persistence of Memory?", "Frida Kahlo", "Salvador Dalí", "René Magritte", "Salvador Dalí", chapter8));
        holdQuestions.put(8020, new Questions("Who painted The Persistence of Memory?", "Frida Kahlo", "Salvador Dalí", "René Magritte", "Salvador Dalí", chapter8));
        holdQuestions.put(8021, new Questions("Who painted The Night Watch?", "Rembrandt", "Gerrit Dou", "Jan Steen", "Rembrandt", chapter8));
        holdQuestions.put(8022, new Questions("What word is used to describe the brightness of a color?", "Intensity", "Exaggeration", "Value", "Intensity", chapter8));
        holdQuestions.put(8023, new Questions("Warm colors include Red, Yellow and...", "Orange", "Green", "Blue", "Orange", chapter8));

        holdQuestions.put(8024, new Questions("Cool colors include Blue, Green and...", "Violet", "Red", "Orange", "Violet", chapter8));
        holdQuestions.put(8025, new Questions("Who directed Melancholia?", "Lars Von Trier", "Christopher Nolan", "Guillermo del Toro", "Lars Von Trier", chapter8));
        holdQuestions.put(8026, new Questions("Who directed Dark City?", "Alex Proyas", "David Fincher", "The Wachowskis'", "Alex Proyas", chapter8));
        holdQuestions.put(8027, new Questions("Which villain haunts and kills persons in their dreams Nightmare on Elm Street?", "Freddy Krueger", "Jason", "Micheal Myers", "Freddy Krueger", chapter8));
        holdQuestions.put(8028, new Questions("Who is the antagonist in Die Hard?", "Hans Gruber", "Hans Landa", "Hans Jacob", "Hans Gruber", chapter8));
        holdQuestions.put(8029, new Questions("The part of a drawing or painting between the foreground and background.", "Middle ground", "Focus point", "Point of interest", "Middle ground", chapter8));
        holdQuestions.put(8030, new Questions("A traditional type of artwork meant to remind the viewer of their own mortality.", "Memento Mori", "divisionism", "anamorphosis", "Memento Mori", chapter8));
        holdQuestions.put(8031, new Questions("Who painted The Kiss?", "Gustave Moreau", "Edvard Munch", "Gustav Klimt", "Gustav Klimt", chapter8));

        //HARD CHAP 8 - RANDOM
        holdQuestions.put(8800, new Questions("Who is the villain in Mutiny On The Bounty (1935)?", "Captain Bligh", "Captain Moreau", "Captain Skull", "Captain Bligh", hardChap8));
        holdQuestions.put(8801, new Questions("Who directed the Laura (1944)?", "Otto Preminger", "John Huston", "Alfred Hitchcock", "Otto Preminger", hardChap8));
        holdQuestions.put(8802, new Questions("Which Russian Romantic painter painted Night in Gurzuf (1891)?", "Konstantin Makovsky", "Viktor Vasnetsov", "Ivan Aivazovsky", "Ivan Aivazovsky", hardChap8));
        holdQuestions.put(8803, new Questions("Who sculpted the Dying Slave between 1513 and 1516?", "Michelangelo", "Donatello", "Michelozzo", "Michelangelo", hardChap8));
        holdQuestions.put(8804, new Questions("Which principle refers to the use of elements in artworks to render visual stability? ", "Balance", "Proportion", "Unity", "Balance", hardChap8));
        holdQuestions.put(8805, new Questions("Which one is NOT a way to classify colors?", "Hue", "Depth", "Intensity", "Depth", hardChap8));
        holdQuestions.put(8806, new Questions("Which is NOT a type of balance in art?", "spectral", "radial", "asymmetrical", "spectral", hardChap8));
        holdQuestions.put(8807, new Questions("Which is a dividing line that works like the point of balance in the balance scale?", "central axis", "balance divider", "focus point", "central axis", hardChap8));
        holdQuestions.put(8808, new Questions("What kind of balance is shown in a work where one half mirrors the other?", "Formal", "Informal", "Mirrored", "Formal", hardChap8));
        holdQuestions.put(8809, new Questions("Which rhythm involves the repetition of wavy or curved lines?", "Flowing", "Progressive", "Random", "Flowing", hardChap8));


    }

    private void geography_quiz() {


        //CHAPTER 1 - NORTH AMERICAN CAPITALS, CITIES AND LANDMARKS
        holdQuestions.put(1000,new Questions("Trinidad and Tobago", "St. Joesph","San Fernando","Port of Spain","Port of Spain",chapter1));
        holdQuestions.put(1001,new Questions("Barbados", "St. James","Bridgetown","Holetown","Bridgetown",chapter1));
        holdQuestions.put(1002,new Questions("Grenada", "Grenville","St. George's","St. Davids","St.George's",chapter1));
        holdQuestions.put(1003,new Questions("Antigua and Barbuda", "St. John's","Codrington","St. Paul","St. John's",chapter1));
        holdQuestions.put(1004,new Questions("Aruba", "Savaneta","San Nicolas","Oranjestad","Oranjestad",chapter1));
        holdQuestions.put(1005,new Questions("Bahamas", "Nassau","George Town","Freeport","Nassau",chapter1));
        holdQuestions.put(1006,new Questions("Belize", "San Ignacio","Belize City","Belmopan","Belmopan",chapter1));
        holdQuestions.put(1007,new Questions("Bermuda", "Hamilton","St. George's","St. John's","Hamilton",chapter1));

        holdQuestions.put(1008,new Questions("Canada", "Toronto","Ottawa","Calgary","Ottawa",chapter1));
        holdQuestions.put(1009,new Questions("Falkland Islands", "Goose Green","Stanley","Hill Cove","Stanley",chapter1));
        holdQuestions.put(1010,new Questions("Costa Rica", "Cartago","San José","Liberia","San José",chapter1));
        holdQuestions.put(1011,new Questions("Cuba", "Havana","Camaguey","Holguin","Havana",chapter1));
        holdQuestions.put(1012,new Questions("El Salvador", "San Miguel","San Salvador","Santa Ana","San Salvador",chapter1));
        holdQuestions.put(1013,new Questions("Dominica", "Roseau","Marigot","Wesley","",chapter1));
        holdQuestions.put(1014,new Questions("United States", "Washington, D.C.","New York","Los Angeles","Washington, D.C.",chapter1));
        holdQuestions.put(1015,new Questions("St. Vincent and the Grenadines", "Kingstown","Calliaqua","Georgetown","Kingstown",chapter1));
        holdQuestions.put(1016,new Questions("Panama ", "Panama City","Colón","Santiago","Panama City",chapter1));

        holdQuestions.put(1017,new Questions("St. Kitts and Nevis", "Basseterre","Charlestown","Nicola Town","Basseterre",chapter1));
        holdQuestions.put(1018,new Questions("Nicaragua ", "Managua","Masaya","León","Managua",chapter1));
        holdQuestions.put(1019,new Questions("Mexico", "Mexico City","Cancún","Mérida","Mexico City",chapter1));
        holdQuestions.put(1020,new Questions("Jamaica ", "Montego Bay","Kingston","Portmore","Kingston",chapter1));
        holdQuestions.put(1021,new Questions("Honduras", "Tegucigalpa","Puerto Cortes","La Ceiba","Tegucigalpa",chapter1));
        holdQuestions.put(1022,new Questions("Haiti", "Jacmel","Cap-Haitien","Port-au-Prince","Port-au-Prince",chapter1));
        holdQuestions.put(1023,new Questions("Guatemala", "Guatemala City","Escuintla","Quetzaltenango","Guatemala City",chapter1));
        holdQuestions.put(1024,new Questions("Dominican Republic", "Puerto Plata","Santo Domingo","La Romana","Santo Domingo",chapter1));

        holdQuestions.put(1025,new Questions("Brazil", "São Paulo","Rio de Janeiro","Brasilia","Brasilia",chapter1));
        holdQuestions.put(1026,new Questions("Argentina", "Buenos Aires","La Plata","Córdoba","Buenos Aires",chapter1));
        holdQuestions.put(1027,new Questions("Colombia", "Bogotá","Medellín","Cartagena","Bogotá",chapter1));
        holdQuestions.put(1028,new Questions("Peru", "Lima","Arequipa","Cusco","Lima",chapter1));
        holdQuestions.put(1029,new Questions("Venezuela", "Cumana","Venezuela City","Caracas","Caracas",chapter1));
        holdQuestions.put(1030,new Questions("Chile", "Santiago","Valparaíso","Arica","Santiago",chapter1));
        holdQuestions.put(1031,new Questions("Ecuador", "Quito","Guayaquil","Cuenca","Quito",chapter1));
        holdQuestions.put(1032,new Questions("Bolivia", "Cochabamba","Sucre","La Paz","La Paz",chapter1));

        holdQuestions.put(1033,new Questions("Paraguay", "Lambaré","Encarnacion","Asunción","Asunción",chapter1));
        holdQuestions.put(1034,new Questions("Uruguay", "Minas","Salto","Montevideo","Montevideo",chapter1));
        holdQuestions.put(1035,new Questions("Guyana", "Bartica","Georgetown","Linden","Georgetown",chapter1));
        holdQuestions.put(1036,new Questions("Suriname", "Paramaribo","Lelydorp","Moengo","Paramaribo",chapter1));


        //HARD CHAP 1 - NORTH AMERICAN CAPITALS, CITIES AND LANDMARKS
        holdQuestions.put(1800,new Questions("Cayenne is the capital of...", "French Guiana","Falkland Islands","Suriname","French Guiana",hardChap1));
        holdQuestions.put(1801,new Questions("Barranquilla is a city in...", "Ecuador","Peru","Colombia","Colombia",hardChap1));
        holdQuestions.put(1802,new Questions("Arima is a city in...", "Chile","Trinidad and Tobago","Venezuela","Trinidad and Tobago",hardChap1));
        holdQuestions.put(1803,new Questions("Where is the largest pitch lake located?", "Trinidad and Tobago","Venezuela","Guyana","Trinidad and Tobago",hardChap1));
        holdQuestions.put(1804,new Questions("Christ the Redeemer is located in which Brazilian city?", "Rio de Janeiro","Brasilia","São Paulo","Rio de Janeiro",hardChap1));
        holdQuestions.put(1805,new Questions("The ancient Machu Picchu citadel is located in...", "Peru","Chile","Mexico","Peru",hardChap1));
        holdQuestions.put(1806,new Questions("Brownsweg is a city located in which country?", "Suriname","Bolivia","Uruguay","Suriname",hardChap1));
        holdQuestions.put(1807,new Questions("Albuquerque is a city in...", "America","Costa Rica","Cuba","America",hardChap1));
        holdQuestions.put(1808,new Questions("Matagalpa is a city in...", "Dominica","Paraguay","Nicaragua","Nicaragua",hardChap1));
        holdQuestions.put(1809,new Questions("Merida Yucatan s a city in...", "Mexico","Ecuador","Venezuela","Mexico",hardChap1));


        // CHAPTER 2 - EUROPEAN CAPITALS, CITIES AND LANDMARKS
        holdQuestions.put(2000,new Questions("Spain", "Seville","Madrid","Barcelona","Madrid",chapter2));
        holdQuestions.put(2001,new Questions("Sweden", "Malmo","Uppsala","Stockholm","Stockholm",chapter2));
        holdQuestions.put(2002,new Questions("Switzerland", "Zürich","Geneva","Lugano","Zürich",chapter2));
        holdQuestions.put(2003,new Questions("Ukraine", "Odesa","Kyiv","Lviv","Kyiv",chapter2));
        holdQuestions.put(2004,new Questions("United Kingdom", "London","Glasgow","Liverpool","London",chapter2));
        holdQuestions.put(2005,new Questions("Slovenia", "Maribor","Koper","Ljubljana","Ljubljana",chapter2));
        holdQuestions.put(2006,new Questions("Slovakia", "Bratislava","Nitra","Poprad","Bratislava",chapter2));
        holdQuestions.put(2007,new Questions("Serbia", "Niš","Subotica","Belgrade","Belgrade",chapter2));

        holdQuestions.put(2008,new Questions("San Marino", "San Marino","Acquaviva","Serravalle","San Marino",chapter2));
        holdQuestions.put(2009,new Questions("Russia", "Novosibirsk","Moscow","Saint Petersburg","Moscow",chapter2));
        holdQuestions.put(2010,new Questions("Romania", "Brașov","Sibiu","Bucharest","Bucharest",chapter2));
        holdQuestions.put(2011,new Questions("Portugal", "Coimbra","Lisbon","Porto","Lisbon",chapter2));
        holdQuestions.put(2012,new Questions("Poland", "Kraków","Wrocław","Warsaw","Warsaw",chapter2));
        holdQuestions.put(2013,new Questions("Norway", "Bergen","Oslo","Trondheim","Oslo",chapter2));
        holdQuestions.put(2014,new Questions("North Macedonia", "Skopje","Ohrid","Bitola","Skopje",chapter2));
        holdQuestions.put(2015,new Questions("Netherlands", "Rotterdam","Amsterdam","Utrecht","Amsterdam",chapter2));

        holdQuestions.put(2016,new Questions("Montenegro", "Nikšić","Podgorica","Pljevlja","Podgorica",chapter2));
        holdQuestions.put(2017,new Questions("Moldova", "Chișinău","Tiraspol","Balti","Chișinău",chapter2));
        holdQuestions.put(2018,new Questions("Malta", "Mdina","Birgu","Valletta","Valletta",chapter2));
        holdQuestions.put(2019,new Questions("Luxembourg", "Luxembourg","Dudelange","Echternach","Luxembourg",chapter2));
        holdQuestions.put(2020,new Questions("Italy", "Rome","Venice","Milan","Rome",chapter2));
        holdQuestions.put(2021,new Questions("Ireland", "Cork","Dublin","Galway","Dublin",chapter2));
        holdQuestions.put(2022,new Questions("Iceland", "Hafnarfjordur","Akureyri","Reykjavík","Reykjavík",chapter2));
        holdQuestions.put(2023,new Questions("Hungary", "Szeged","Debrecen","Budapest","Budapest",chapter2));

        holdQuestions.put(2024,new Questions("Greece", "Athens","Corinth","Patras","Athens",chapter2));
        holdQuestions.put(2025,new Questions("Germany", "Frankfurt","Berlin","Munich","Berlin",chapter2));
        holdQuestions.put(2026,new Questions("France", "Lyon","Bordeaux","Paris","Paris",chapter2));
        holdQuestions.put(2027,new Questions("Finland", "Tampere","Helsinki","Turku","Helsinki",chapter2));
        holdQuestions.put(2028,new Questions("Denmark", "Aalborg","Aarhus","Copenhagen","Copenhagen",chapter2));
        holdQuestions.put(2029,new Questions("Czechia", "Ostrava","Prague","Brno","Prague",chapter2));
        holdQuestions.put(2030,new Questions("Croatia", "Zagreb","Zadar","Split","Zagreb",chapter2));
        holdQuestions.put(2031,new Questions("Belgium", "Brussels","Bruges","Ghent","Brussels",chapter2));
        holdQuestions.put(2032,new Questions("Austria", "Salzburg","Vienna","Graz","Vienna",chapter2));



        //HARD CHAP 2 - EUROPEAN CAPITALS, CITIES AND LANDMARKS
        holdQuestions.put(2800,new Questions("The capital of Andorra is...", "Canillo","Andorra la Vella","Encamp","Andorra la Vella",hardChap2));
        holdQuestions.put(2801,new Questions("The capital of Albania is...", "Durrës","Berat","Tirana","Tirana",hardChap2));
        holdQuestions.put(2802,new Questions("Minsk is the capital of...", "Andorra","Albania","Belarus","Belarus",hardChap2));
        holdQuestions.put(2803,new Questions("The capital of Bulgaria is...", "Sofia","Polvdiv","Varna","Sofia",hardChap2));
        holdQuestions.put(2804,new Questions("The capital of Bosnia and Herzegovina is...", "Sarajevo","Tuzia","Mostar","Sarajevo",hardChap2));
        holdQuestions.put(2805,new Questions("Tallinn is the capital of...", "Bosnia and Herzegovina","Belarus","Estonia","Estonia",hardChap2));
        holdQuestions.put(2806,new Questions("The capital Lithuania is...", "Klaipėda","Kaunas","Vilnius","Vilnius",hardChap2));
        holdQuestions.put(2807,new Questions("Vaduz is the capital of...", "Liechtenstein","Estonia","Latvia","Liechtenstein",hardChap2));
        holdQuestions.put(2808,new Questions("Riga is the capital of...", "Andorra","Lithuania","Latvia","Latvia",hardChap2));




        // CHAPTER 3 - AFRICAN CAPITALS, CITIES AND LANDMARKS
        holdQuestions.put(3000,new Questions("Nigeria", "Ibadan","Lagos","Abuja","Abuja",chapter3));
        holdQuestions.put(3001,new Questions("Ghana", "Kumasi","Cape Coast","Accra","Accra",chapter3));
        holdQuestions.put(3002,new Questions("Algeria", "Oran","Algiers","Setif","Algiers",chapter3));
        holdQuestions.put(3003,new Questions("Ethiopia", "Harar","Dire Dawa","Addis Abada","Addis Abada",chapter3));
        holdQuestions.put(3004,new Questions("Eritrea", "Asmara","Mitsiwa","Āssab","Asmara",chapter3));
        holdQuestions.put(3005,new Questions("Madagascar", "Toamasina","Mahajanga","Antananarivo","Antananarivo",chapter3));
        holdQuestions.put(3006,new Questions("Mali", "Mopti","Timbuktu","Bamako","Bamako",chapter3));
        holdQuestions.put(3007,new Questions("Gambia", "Sukuta","Banjul","Bakau","Banjul",chapter3));

        holdQuestions.put(3008,new Questions("Democratic Republic of Congo", "Kinshasa","Bukavu","Goma","Kinshasa",chapter3));
        holdQuestions.put(3009,new Questions("Tanzania", "Dar es Salaam","Mwanza","Dodama","Dodama",chapter3));
        holdQuestions.put(3010,new Questions("Senegal", "Saint Louis","Dakar","Touba","Dakar",chapter3));
        holdQuestions.put(3011,new Questions("Egypt", "Cairo","Aswan","Luxor","Cairo",chapter3));
        holdQuestions.put(3012,new Questions("Uganda", "Mbarara","Kampala","Jinja","Kampala",chapter3));
        holdQuestions.put(3013,new Questions("South Sudan", "Juba","Yei","Wau","Juba",chapter3));
        holdQuestions.put(3014,new Questions("Botswana", "Maun","Gaborone","Francistown","Gaborone",chapter3));
        holdQuestions.put(3015,new Questions("Sierra Leone", "Koidu","Freetown","Kenema","Freetown",chapter3));

        holdQuestions.put(3016,new Questions("Zimbabwe", "Harare","Bulawayo","Mutare","Harare",chapter3));
        holdQuestions.put(3017,new Questions("Rwanda", "Musanze","Rwamagana","Kigali","Kigali",chapter3));
        holdQuestions.put(3018,new Questions("Togo", "Lomé","Sokode","Mango","Lomé",chapter3));
        holdQuestions.put(3019,new Questions("Angola", "Luanda","Lobito","Huambo","Luanda",chapter3));
        holdQuestions.put(3020,new Questions("Liberia", "Kakata","Monrovia","Gbarnga","Monrovia",chapter3));
        holdQuestions.put(3021,new Questions("Somalia", "Mogadishu","Kismayo","Baidoa","Mogadishu",chapter3));
        holdQuestions.put(3022,new Questions("Maputo is the capital of...", "Mozambique","Malawi","Burkina Faso","Mozambique",chapter3));
        holdQuestions.put(3023,new Questions("Kenya", "Nairobi","Mombasa","Kisumu","Nairobi",chapter3));

        holdQuestions.put(3024,new Questions("Chad", "Sarh","Abeche","N'Djamena","N'Djamena",chapter3));
        holdQuestions.put(3025,new Questions("Niger", "Agadez","Niamey","Zinder","Niamey",chapter3));
        holdQuestions.put(3026,new Questions("Morocco", "Rabat","Fes","Casablanca","Rabat",chapter3));
        holdQuestions.put(3027,new Questions("Libya", "Sirte","Benghazi","Tripoli","Tripoli",chapter3));

        holdQuestions.put(3028,new Questions("Cameroon", "Bamenda","Douala","Yaoundé","Yaoundé",chapter3));
        holdQuestions.put(3029,new Questions("Togo", "Lomé","Kpalime","Sokode","Lomé",chapter3));
        holdQuestions.put(3030,new Questions("Sudan", "Khartoum","Port Sudan","Omdurman","Khartoum",chapter3));
        holdQuestions.put(3031,new Questions("Gabon", "Franceville","Libreville","Oyem","Libreville",chapter3));



        // HARD CHAP 3 - AFRICAN CAPITALS, CITIES AND LANDMARKS
        holdQuestions.put(3800,new Questions("The capital of Malawi is...", "Lilongwe","Mzuzu","Zomba","Lilongwe",hardChap3));
        holdQuestions.put(3801,new Questions("The capital of Burkina Faso is...", "Ouagadougou","Banfora","Koudougou","Ouagadougou",hardChap3));
        holdQuestions.put(3802,new Questions("Praia is the capital of...", "Malawi","Burkina Faso","Cape Verde","Cape Verde",hardChap3));
        holdQuestions.put(3803,new Questions("The capital of Burundi is...", "Bujumbura","Gitega","Muyinga","Gitega",hardChap3));
        holdQuestions.put(3804,new Questions("Windhoek is the capital of...", "Nambia","Burundi","Cape Verde","Nambia",hardChap3));
        holdQuestions.put(3805,new Questions("Timbuktu is located in which African country?", "Congo","Mali","Somalia","Mali",hardChap3));
        holdQuestions.put(3806,new Questions("Kumasi is a city located in...", "Ghana","Ivory Coast","South Africa","Mali",hardChap3));
        holdQuestions.put(3807,new Questions("Nouakchott is the city capital of...", "Mauritania","Ivory Coast","Cape Verde","Mauritania",hardChap3));
        holdQuestions.put(3808,new Questions("The Republic of Congo capital is...", "Brazzaville","Muyinga","Kinshasa","Brazzaville",hardChap3));



        // CHAPTER 4 - ASIA CAPITALS
        holdQuestions.put(4000,new Questions("China", "Beijing","Wuhan","Shanghai","Beijing",chapter4));
        holdQuestions.put(4001,new Questions("Turkey", "Ankara","İstanbul","İzmir","Ankara",chapter4));
        holdQuestions.put(4002,new Questions("India", "Mumbai","Chennai","New Delhi","New Delhi",chapter4));
        holdQuestions.put(4003,new Questions("Thailand", "Phuket","Bangkok","Chiang Mai","",chapter4));
        holdQuestions.put(4004,new Questions("South Korea", "Daegu","Seoul","Busan","Seoul",chapter4));
        holdQuestions.put(4005,new Questions("North Korea", "Chongjin","Nampo","Pyongyang","Pyongyang",chapter4));
        holdQuestions.put(4006,new Questions("Sri Lanka", "Kotte","Colombo","Kandy","Kotte",chapter4));
        holdQuestions.put(4007,new Questions("Indonesia", "Jakarta","Bandung","Medan","Jakarta",chapter4));

        holdQuestions.put(4008,new Questions("Pakistan", "Karachi","Lahore","Islamabad","Islamabad",chapter4));
        holdQuestions.put(4009,new Questions("Qatar", "Doha","Al Dafna","Al Daayen","Doha",chapter4));
        holdQuestions.put(4010,new Questions("Nepal", "Pokhara","Kathmandu","Lalitpur","Kathmandu",chapter4));
        holdQuestions.put(4011,new Questions("Kuwait", "Kuwait City","Al Jahra","Ahmadi","Kuwait City",chapter4));
        holdQuestions.put(4012,new Questions("Bahrain", "Manama","Isa Town","Riffa","Manama",chapter4));
        holdQuestions.put(4013,new Questions("Yemen", "Aden","Taizz","Sana'a","Sana'a",chapter4));
        holdQuestions.put(4014,new Questions("Cambodia", "Krong Kaeb","Phnom Penh","Samraong","Phnom Penh",chapter4));
        holdQuestions.put(4015,new Questions("Singapore", "Singapore","Serangoon","Jurong","Singapore",chapter4));

        holdQuestions.put(4016,new Questions("Japan", "Tokyo","Kobe","Kyoto","Tokyo",chapter4));
        holdQuestions.put(4017,new Questions("Laos", "Vientiane","Pakse","Vang Vieng","Vientiane",chapter4));
        holdQuestions.put(4018,new Questions("Mongolia", "Ulaanbaatar","Ulaangom","Darkhan","Ulaanbaatar",chapter4));
        holdQuestions.put(4019,new Questions("Armenia", "Gyumri","Yerevan","Vanadzor","Yerevan",chapter4));
        holdQuestions.put(4020,new Questions("Jordan", "Amman","Jerash","Aqaba","Amman",chapter4));
        holdQuestions.put(4021,new Questions("Azerbaijan", "Shirvan","Ganja","Baku","Baku",chapter4));
        holdQuestions.put(4022,new Questions("Turkmenistan", "Turkmenabat","Ashgabat","Tejen","Ashgabat",chapter4));
        holdQuestions.put(4023,new Questions("Kazakhstan", "Semey","Nur-Sultan","Almaty","Nur-Sultan",chapter4));

        holdQuestions.put(4024,new Questions("Vietnam", "Da Nang","Hanoi","Can Tho","Hanoi",chapter4));
        holdQuestions.put(4025,new Questions("Myanmar", "Yangon","Naypyitaw","Mandalay","Naypyitaw",chapter4));
        holdQuestions.put(4026,new Questions("Saudi Arabia", "Riyadh","Jeddah","Medina","Riyadh",chapter4));
        holdQuestions.put(4027,new Questions("Taiwan", "Taipei City","Tainan City","Kaohsiung","Taipei City",chapter4));
        holdQuestions.put(4028,new Questions("Uzbekistan", "Khiva","Samarkand","Tashkent","Tashkent",chapter4));
        holdQuestions.put(4029,new Questions("Bhutan", "Paro","Punakha","Thimphu","Thimphu",chapter4));
        holdQuestions.put(4030,new Questions("Georgia", "Tbilisi","Atlanta","Athens","Tbilisi",chapter4));
        holdQuestions.put(4031,new Questions("Tajikistan", "Dushanbe","Kulob","Khujand","Dushanbe",chapter4));


        // HARD CHAP 4 - ASIAN CAPITALS, CITIES AND LANDMARKS
        holdQuestions.put(4800,new Questions("Bandar Seri Begawan is the capital of...", "Timor-Leste","Brunei","Maldives","Brunei",hardChap4));
        holdQuestions.put(4801,new Questions("The capital of Maldives is...", "Minicoy Island","Malé","Maafannu","Malé",hardChap4));
        holdQuestions.put(4802,new Questions("The capital of Timor-Leste is...", "Aileu","Dili","Lospalos","Dili",hardChap4));
        holdQuestions.put(4803,new Questions("Lod is a city found in...", "Israel","Syria","Jordon","Israel",hardChap4));
        holdQuestions.put(4804,new Questions("Kolkata is a city in...", "India","China","Sri Lanka","India",hardChap4));
        holdQuestions.put(4805,new Questions("Anuradhapura is a city in...", "Saudi Arabia","Sri Lanka","Bangladesh","Sri Lanka",hardChap4));
        holdQuestions.put(4806,new Questions("Shenzhen is a city in...", "China","Japan","Malaysia","China",hardChap4));
        holdQuestions.put(4807,new Questions("What is China's third most populated city?", "Beijing","Tianjin","Chongqing","Chongqing",hardChap4));
        holdQuestions.put(4808,new Questions("Pakistan's most populated city is...", "Lahore","Karachi","Faisalabad","Karachi",hardChap4));
        holdQuestions.put(4809,new Questions("Which country does the city Yangon belong to?", "Myanmar","Malaysia","Thailand","Myanmar",hardChap4));


        // CHAPTER 5 - OCEANIA CAPITALS, CITIES AND LANDMARKS
        holdQuestions.put(5000,new Questions("Australia", "Canberra","Sydney","Melbourne","Canberra",chapter5));
        holdQuestions.put(5001,new Questions("Fiji", "Suzi","Suva","Solo","Suva",chapter5));
        holdQuestions.put(5002,new Questions("Kiribati", "Tarawa","Tuvalu","Majuro","Tarawa",chapter5));
        holdQuestions.put(5003,new Questions("Marshall Islands", "Suva","Tuvalu","Majuro","Majuro",chapter5));
        holdQuestions.put(5004,new Questions("Micronesia", "Kolonia","Palikir","Colonia","Palikir",chapter5));
        holdQuestions.put(5005,new Questions("Nauru", "Yaren","Meneng","Aiwo","Yaren",chapter5));
        holdQuestions.put(5006,new Questions("New Zealand", "Wellington","Christchurch","Auckland","Wellington",chapter5));
        holdQuestions.put(5007,new Questions("Palau", "Melekeok","Ngerulmud","Koror","Ngerulmud",chapter5));

        holdQuestions.put(5008,new Questions("Papua New Guinea", "Port Moresby","Goroka","Madang","Port Moresby",chapter5));
        holdQuestions.put(5009,new Questions("Samoa", "Asau","Apia","Afiga","Apia",chapter5));
        holdQuestions.put(5010,new Questions("Solomon Islands", "Honiara","Gizo","Lata","Honiara",chapter5));
        holdQuestions.put(5011,new Questions("Tonga", "Pangai","Nuku'alofa","Neiafu","Nuku'alofa",chapter5));
        holdQuestions.put(5012,new Questions("Tuvalu", "Alapi","Lolua","Funafuti","Funafuti",chapter5));
        holdQuestions.put(5013,new Questions("Vanuatu", "Port-Vila","Luganville","Norsup","Port-Vila",chapter5));


        // HARD CHAP 5 - OCEANIA CAPITALS, CITIES AND LANDMARKS
        holdQuestions.put(5800,new Questions("Dunedin is a city found in...", "New Zealand","Australia","Tonga","New Zealand",hardChap5));
        holdQuestions.put(5801,new Questions("Lower Hutt is a town located in...", "Australia","New Zealand","Fiji","New Zealand",hardChap5));
        holdQuestions.put(5802,new Questions("What is the capital of Cook Islands?", "Arutanga","Amuri","Avarua District","Avarua District",hardChap5));
        holdQuestions.put(5803,new Questions("Koror is a city in...", "Palau","Nauru","Samoa","Palau",hardChap5));
        holdQuestions.put(5804,new Questions("Luganville is a city in...", "Vanuatu","Samoa","Palau","Vanuatu",hardChap5));


        // CHAPTER 6 - AFRICA
        holdQuestions.put(6000,new Questions("In which country is the Serengeti Park located", "Uganda","Tanzania","Kenya","Tanzania",chapter6));
        holdQuestions.put(6001,new Questions("Which country was formerly called Rhodesia?", "Somalia","Zimbabwe","Algeria","Zimbabwe",chapter6));
        holdQuestions.put(6002,new Questions("How many deserts are there in Africa?", "3","4","7","3",chapter6));
        holdQuestions.put(6003,new Questions("In which country would you find the city of Timbuktu?", "Mali","Niger","Chad","Mali",chapter6));
        holdQuestions.put(6004,new Questions("Which African river flows into the Indian Ocean?", "Zambezi River","Luangwa River","Kowie River","Zambezi River",chapter6));
        holdQuestions.put(6005,new Questions("Which of the following country is located on the Horn of Africa?", "Ethiopia","Togo","Ghana","Ethiopia",chapter6));
        holdQuestions.put(6006,new Questions("Which is the highest mountain peak of the Sahara?", "Mount Virunga","Mount Karisimbi","Mount Koussi","Mount Koussi",chapter6));
        holdQuestions.put(6007,new Questions("Which is the highest peak in Africa?", "Mount Koussi","Kilimanjaro","Mount Elgon","Kilimanjaro",chapter6));

        holdQuestions.put(6008,new Questions("Which language is most commonly spoken in South Africa?", "Zulu","Xhosa","Swahili","Zulu",chapter6));
        holdQuestions.put(6009,new Questions("Which is the largest lake in Ethiopia?", "Ziway Lake","Lake Turkana","Lake Tana","Lake Tana",chapter6));
        holdQuestions.put(6010,new Questions("Which is the easternmost extension of Africa?", "Tigray Region","Somali","Horn of Africa","Horn of Africa",chapter6));
        holdQuestions.put(6011,new Questions("What is the largest ethnic group in South Africa?", "Xhosa","Zulu","Tswana","Zulu",chapter6));
        holdQuestions.put(6012,new Questions("Which of the following is home to the Zulu people of Africa?", "Eastern Cape","Gauteng","KwaZulu-Natal","KwaZulu-Natal",chapter6));
        holdQuestions.put(6013,new Questions("Which is the largest country in Africa?", "Algeria","DRC","Sudan","Algeria",chapter6));
        holdQuestions.put(6014,new Questions("What is the highest waterfall in Africa?", "Tugela Falls","Victoria Falls","Kalambo Falls","Tugela Falls",chapter6));
        holdQuestions.put(6015,new Questions("The African country Sudan derives from the Arabic expression bilād al-sūdān. What is the meaning of this expression?", "land of blacks","land of trees","land of sand","land of blacks",chapter6));

        holdQuestions.put(6016,new Questions("Lomé is the capital of which African country?", "Togo","Sudan","Burkina Faso","Togo",chapter6));
        holdQuestions.put(6017,new Questions("The Afrikaans language was derived from which European language?", "German","Dutch","French","Dutch",chapter6));
        holdQuestions.put(6018,new Questions("Bamako is the capital of which African country?", "Mali","Eritrea","Libya","",chapter6));
        holdQuestions.put(6019,new Questions("The city of Leptis Magna is located in which African country?", "Libya","Mali","Ethiopia","Libya",chapter6));
        holdQuestions.put(6020,new Questions("Harare is the capital of which African country?", "Zimbabwe","Togo","Eritrea","Zimbabwe",chapter6));
        holdQuestions.put(6021,new Questions("How many capital cities does South Africa possess?", "2","1","3","3",chapter6));
        holdQuestions.put(6022,new Questions("What is the second highest mountain in Africa?", "Mawenzi","Mount Kenya","Mount Stanley","Mount Kenya",chapter6));
        holdQuestions.put(6023,new Questions("Mount Kilimanjaro exists in which African nation?", "Tanzania","Kenya","Uganda","Tanzania",chapter6));

        holdQuestions.put(6024,new Questions("Swaziland is also known as...", "Eswatini","Lagos","Pretoria","Eswatini",chapter6));
        holdQuestions.put(6025,new Questions("The current capital of Zimbabwe Harare was formerly known as?", "Salisbury","Pretoria","Nairobi","Salisbury",chapter6));
        holdQuestions.put(6026,new Questions("N’Djamena is the capital of which of the following countries?", "Ghana","Kenya","Chad","Chad",chapter6));
        holdQuestions.put(6027,new Questions("Africa has three deserts, the Kalahari Desert, Sahara Desert and which other desert?", "Namib Desert","Taklamakan Desert","Gobi Desert","Namib Desert",chapter6));
        holdQuestions.put(6028,new Questions("Where is Victoria Falls located?", "Zambia","Egypt","South Africa","Zambia",chapter6));
        holdQuestions.put(6029,new Questions("Which of the following separates Africa from Europe?", "Red Sea","Caspian Sea","Mediterranean Sea","Mediterranean Sea",chapter6));
        holdQuestions.put(6030,new Questions("What is the longest river in Africa?", "Nile River","Congo River","Niger River","Nile River",chapter6));
        holdQuestions.put(6031,new Questions("What is the largest African lake?", "Lake Victoria","Lake Tanganyika","Lake Malawi","Lake Victoria",chapter6));


        // HARD CHAP 6 - AFRICA
        holdQuestions.put(6800,new Questions("Most populated country in Africa is...", "Nigeria","Ethiopia","Egypt","Nigeria",hardChap6));
        holdQuestions.put(6801,new Questions("Known for its flat-top, Table Mountain is located in which African country?", "Uganda","South Africa","Mali","South Africa",hardChap6));
        holdQuestions.put(6802,new Questions("Maasai Mara National Reserve can be found in...", "Kenya","Uganda","Ethiopia","Kenya",hardChap6));
        holdQuestions.put(6803,new Questions("Which country does NOT border Mali?", "Burkina Faso","Algeria","Somalia","Somalia",hardChap6));
        holdQuestions.put(6804,new Questions("Tarangire National Park is located in...", "Kenya","Tanzania","Uganda","Tanzania",hardChap6));
        holdQuestions.put(6805,new Questions("Which country borders Chad?", "Cameroon","Angola","Senegal","Cameroon",hardChap6));
        holdQuestions.put(6806,new Questions("Ngorongoro Conservation Area is located in which country?", "Mali","Nigeria","Tanzania","Tanzania",hardChap6));
        holdQuestions.put(6807,new Questions("Which country does NOT border Zambia?", "Namibia","Angola","Zimbabwe","Namibia",hardChap6));
        holdQuestions.put(6808,new Questions("In which country does the Zambezi river NOT flow through?", "Zambia","Zimbabwe","Kenya","Kenya",hardChap6));
        holdQuestions.put(6809,new Questions("The Tugela Falls is a waterfall in...", "South Africa","Nigeria","Kenya","South Africa",hardChap6));


        // CHAPTER 7 - ASIA
        holdQuestions.put(7000,new Questions("Which asian country does the Mekong River not flow through?", "Vietnam","Laos","Mongolia","Mongolia",chapter7));
        holdQuestions.put(7001,new Questions("In what Asian country can the city Hefei be found?", "China","Japan","India","China",chapter7));
        holdQuestions.put(7002,new Questions("What is the longest flowing river in Asia?", "Yangtze River","Mekong River","Ganges River","Yangtze River",chapter7));
        holdQuestions.put(7003,new Questions("The Yangtze River flows entirely in which country?", "China","Pakistan","Japan","China",chapter7));
        holdQuestions.put(7004,new Questions("Which of these is not a Japanese city?", "Tokyo","Osaka","Kolkata","Kolkata",chapter7));
        holdQuestions.put(7005,new Questions("What is the capital of Bangladesh?", "Khulna","Chattogram","Dhaka","Dhaka",chapter7));
        holdQuestions.put(7006,new Questions("What is the capital of Indonesia?", "Jakarta","Makassar","Semarang","Jakarta",chapter7));
        holdQuestions.put(7007,new Questions("What is the capital of Thailand?", "Bangkok","Chiang Mai","Pattaya City","Bangkok",chapter7));

        holdQuestions.put(7008,new Questions("Siam is the former name of which Asian country?", "Thailand","Qatar","Bangdalesh","Thailand",chapter7));
        holdQuestions.put(7009,new Questions("Which of these is the largest freshwater lake in Japan?", "Lake Biwa","Lake Tazawa","Lake Kawaguchi","Lake Biwa",chapter7));
        holdQuestions.put(7010,new Questions("The Borobudur monument exists in which Asain country? ", "Indonesia","Thailand","India","Indonesia",chapter7));
        holdQuestions.put(7011,new Questions("Which ancient trade route linked the Western world with Asia?", "Silk Road","Appian Way","Via Maris","Silk Road",chapter7));
        holdQuestions.put(7012,new Questions("The tallest mountain from sea level is found in Asia. What is it called?", "Mount Everest","Kangchenjunga","Mount Kinabalu","Mount Everest",chapter7));
        holdQuestions.put(7013,new Questions("Which country is Mount Fuji in?", "China","Pakistan","Japan","Japan",chapter7));
        holdQuestions.put(7014,new Questions("Kuala Lumpur is the capital city of which asian country?", "Malaysia","Thailand","Japan","Malaysia",chapter7));
        holdQuestions.put(7015,new Questions("Damascus is the capital city of which asian country?", "Iran","Iraq","Syria","Syria",chapter7));

        holdQuestions.put(7016,new Questions("Which asian nation is sometimes referred to as \"Land of the Rising Sun\"?", "Japan","India","China","Japan",chapter7));
        holdQuestions.put(7017,new Questions("The word thailand can be translated to \"Land of the....\"", "free","innocent","trees","free",chapter7));
        holdQuestions.put(7018,new Questions("The Taj Mahal is located in....", "India","Pakistan","Sri Lanka","India",chapter7));
        holdQuestions.put(7019,new Questions("The Shwedagon Buddhist Pagoda is located in....", "Myanmar","Philippines","Malaysia","Myanmar",chapter7));
        holdQuestions.put(7020,new Questions("The Gobi desert is located in....", "East Asia","West Asia","Southern Asia","East Asia",chapter7));
        holdQuestions.put(7021,new Questions("Which is the largest desert in Asia by area extent?", "Gobi Desert","Arabian Desert","Karakum Desert","Arabian Desert",chapter7));
        holdQuestions.put(7022,new Questions("Which of the following countries are NOT apart of the Asian continent?", "Iraq","Iran","Eritrea","Eritrea",chapter7));
        holdQuestions.put(7023,new Questions("Which of the following countries are NOT apart of the Asian continent?", "Saudi Arabia","Afghanistan","Tunisia","Tunisia",chapter7));

        holdQuestions.put(7024,new Questions("What is the largest continent in the world?", "Asia","Africa","Europe","Asia",chapter7));
        holdQuestions.put(7025,new Questions("Which of the following river does NOT belong in the Asian continent?", "Ubangi River","Red RIver","Euphrates River","Ubangi River",chapter7));
        holdQuestions.put(7026,new Questions("Which of these nations does not border Thailand?", "China","Laos","Cambodia","China",chapter7));
        holdQuestions.put(7027,new Questions("Which of these nations does not border India?", "Russia","Nepal","China","Russia",chapter7));
        holdQuestions.put(7028,new Questions("What is the capital of Oman?", "Muscat","Vienna","Kyiv","Muscat",chapter7));
        holdQuestions.put(7029,new Questions("What is the capital of Turkmenistan?", "Muscat","Valletta","Ashgabat","Ashgabat",chapter7));
        holdQuestions.put(7030,new Questions("The Tian Tan Buddha statue is located in..", "Hong Kong","Japan","India","Hong Kong",chapter7));
        holdQuestions.put(7031,new Questions("The largest religious structure Angkor Wat is located in...", "Cambodia","Vietnam","Thailand","Cambodia",chapter7));


        // HARD CHAP 7 - ASIA
        holdQuestions.put(7800,new Questions("Najran is a city in...", "Saudi Arabia","Malaysia","Jordon","Saudi Arabia",hardChap7));
        holdQuestions.put(7801,new Questions("Known for it's high salinity and lack of living creatures, the Dead sea lies between which two asian countries?", "Israel and Jordon","India and Pakistan","Mongolia and China","Israel and Jordon",hardChap7));
        holdQuestions.put(7802,new Questions("Uliastai also knowwn as Javkhlant is a city found in...", "China","Mongolia","Japan","Mongolia",hardChap7));
        holdQuestions.put(7803,new Questions("Measuring 3,776 metres, this active volcano is Japan's tallest peak. What is the name of this mountain?", "Mount Fiji","Mount Kita","Mount Yari","Mount Fiji",hardChap7));
        holdQuestions.put(7804,new Questions("The rock-cut Masrur Temples formed in the 8th century is found in what asian country?", "India","Cambodia","Nepal","India",hardChap7));
        holdQuestions.put(7805,new Questions("The Sree Padmanabhaswamy Temple is found in...", "Cambodia","India","Thailand","India",hardChap7));
        holdQuestions.put(7806,new Questions("This country borders the South China Sea and Malaysia. What is the name of this country?", "Brunei","Indonesia","Thailand","Brunei",hardChap7));
        holdQuestions.put(7807,new Questions("Which country does NOT border Afghanistan?", "Pakistan","Saudi Arabia","Iran","",hardChap7));
        holdQuestions.put(7808,new Questions("Which country borders India?", "Bhutan","Malaysia","Cambodia","Bhutan",hardChap7));
        holdQuestions.put(7809,new Questions("Which country does NOT border Thailand?", "Myanmar","Laos","Singapore","Singapore",hardChap7));


        // CHAPTER 8 - EUROPE
        holdQuestions.put(8000,new Questions("Which peak is the highest active volcano in Europe?", "Mount Etna","Santorini","Teide","Mount Etna",chapter8));
        holdQuestions.put(8001,new Questions("What is the longest river in Europe?", "Volga River","Danube River","Dnieper River","Volga River",chapter8));
        holdQuestions.put(8002,new Questions("Mont Blanc is located in which country?", "France","Greece","Spain","France",chapter8));
        holdQuestions.put(8003,new Questions("What is the capital of Romania?", "Bucharest","Bulgaria","Vienna","Bucharest",chapter8));
        holdQuestions.put(8004,new Questions("What is the capital of Finland?", "Vilnius","Helsinki","Chișinău","Helsinki",chapter8));
        holdQuestions.put(8005,new Questions("What is the highest mountain in Europe?", "Monte Rosa","Mount Kazbek","Mount Elbrus","Mount Elbrus" ,chapter8));
        holdQuestions.put(8006,new Questions("What is the largest country in Europe?", "Russia","Germany","Spain","Russia",chapter8));
        holdQuestions.put(8007,new Questions("Lithuania, Latvia and Estonia are together called the...", "United Kingdom","Baltic states","Scandinavian states","Baltic states",chapter8));

        holdQuestions.put(8008,new Questions("Which chain of mountains separates Europe and Asia?", "Virunga Mountains","Pamir Mountains","Ural Mountains","Ural Mountains",chapter8));
        holdQuestions.put(8009,new Questions("Europe shares its landmass with what other continent?", "Africa","Oceania","Asia","Asia",chapter8));
        holdQuestions.put(8010,new Questions("What is the most populated European country?", "Russia","France","Germany","Russia",chapter8));
        holdQuestions.put(8011,new Questions("What is the capital of Norway?", "Oslo","Valletta","Kyiv","Oslo",chapter8));
        holdQuestions.put(8012,new Questions("What is the capital of Ireland?", "Dublin","Valletta","Vienna","Dublin",chapter8));
        holdQuestions.put(8013,new Questions("What is the name of the peninsula where you can find both Portugal and Spain? ", "Iberian","Italian","Balkan","Iberian",chapter8));
        holdQuestions.put(8014,new Questions("How are Norway, Sweden, Iceland and Denmark called together?", "Baltic","Cold countries","Scandinavian","Scandinavian",chapter8));
        holdQuestions.put(8015,new Questions("Madrid is the capital of which country?", "Spain","Portugal","Italy","Spain",chapter8));

        holdQuestions.put(8016,new Questions("What is the largest lake in Europe?", "Lake Vänern","Lake Ladoga","Lake Onega","Lake Ladoga",chapter8));
        holdQuestions.put(8017,new Questions("Berlin is capital of...?", "United Kingdom","Netherlands","Germany","Germany",chapter8));
        holdQuestions.put(8018,new Questions("What is the capital of Iceland?", "Helsinki","Oslo","Reykjavík","Reykjavík",chapter8));
        holdQuestions.put(8019,new Questions("In which country can you find the Plitvice Lakes National Park?", "Switzerland","Finland","Croatia","Croatia",chapter8));
        holdQuestions.put(8020,new Questions("What is the longest river within Czechia?", "Vltava River","Nile River","Tigris River","Vltava River",chapter8));
        holdQuestions.put(8021,new Questions("What is the capital of Poland?", "Ljubljana","Belgrade","Warsaw","Warsaw",chapter8));
        holdQuestions.put(8022,new Questions("What is the second-longest river in Europe?", "Danube River","Oka River","Ural River","Danube River",chapter8));
        holdQuestions.put(8023,new Questions("Schönbrunn Palace is located in....", "Germany","Netherlands","Austria","Austria",chapter8));

        holdQuestions.put(8024,new Questions("Amsterdam is the capital of..", "Netherlands","Denmark","Norway","Netherlands",chapter8));
        holdQuestions.put(8025,new Questions("The Cologne Cathedral exists in...", "Germany","Italy","France","Germany",chapter8));
        holdQuestions.put(8026,new Questions("Acropolis of Athens monument is located...", "Greece","Italy","Spain","Greece",chapter8));
        holdQuestions.put(8027,new Questions("Which country can you find between Spain and France?", "Andorra","North Macedonia","Austria","Andorra country",chapter8));
        holdQuestions.put(8028,new Questions("Zagreb is the capital of..", "Switzerland","Croatia","Hungary","Croatia",chapter8));
        holdQuestions.put(8029,new Questions("In what country is Gdańsk located?", "Poland","Greece","North Macedonia","Poland",chapter8));
        holdQuestions.put(8030,new Questions("How many countries does Slovakia share a border with?", "6","9","4","4",chapter8));
        holdQuestions.put(8031,new Questions("Copenhagen is the capital of...", "Denmark","Poland","Finland","Denmark",chapter8));


        // HARD CHAP 8 - EUROPE
        holdQuestions.put(8800,new Questions("Wiesbaden is a city in...", "Denmark","Germany","Austria","Germany",hardChap8));
        holdQuestions.put(8801,new Questions("Chur is an old city in...", "Switzerland","Germany","Netherlands","Switzerland",hardChap8));
        holdQuestions.put(8802,new Questions("The Skógafoss waterfall is located in...", "Norway","Iceland","Sweden","Iceland",hardChap8));
        holdQuestions.put(8803,new Questions("Which country does NOT border Poland?", "Czech Republic","Belarus","Romania","Romania",hardChap8));
        holdQuestions.put(8804,new Questions("Which country does NOT border Austria?", "Ukraine","Slovenia","Italy","Ukraine",hardChap8));
        holdQuestions.put(8805,new Questions("The ancient monument Stonehenge is located in what U.K country?", "England","Scotland","Ireland","England",hardChap8));
        holdQuestions.put(8806,new Questions("The Baltic Sea borders how many European countries?", "9","11","7","9",hardChap8));
        holdQuestions.put(8807,new Questions("How many countries does Romania border?", "3","5","7","5",hardChap8));
        holdQuestions.put(8808,new Questions("Second least populated country in Europe is...", "Vatican City","San Marino","Liechtenstein","San Marino",hardChap8));
        holdQuestions.put(8809,new Questions("This peak rises up to 2,544 metres in Romania. It is Romania's tallest peak. Which is it?", "Moldoveanu Peak","Negoiu Peak","Dreitorspitze Peak","Moldoveanu Peak",hardChap8));


    }
    /*

    private void literature_quiz() {

    }

    private void bible_quiz() {

    }

    private void movie_quiz() {

    }

 */


    private void fillQuestionsTable(int table_num) {

        //science_quiz();

        switch(table_num) {
            case 0:
                science_quiz();
                break;
            case 1:
                music_quiz();
                break;
            case 2:
                history_quiz();
                break;
            case 3:
                math_quiz();
                break;
            case 4:
                arts_quiz();
                break;
            case 5:
                geography_quiz();
                break;
/*
            case 7:
                tech_quiz();
                break;
            case 8:
                geography_quiz();
                break;
            case 9:
                literature_quiz();
                break;
            case 10:
                bible_quiz();
                break;
            case 11:
                movie_quiz();
                break;


 */

        }


        //get question from the method called in switch above
        //This was throwing null because of I was starting int i at 0, but
        //holdQuestions has no key at 0, therefore null was thrown.

        ArrayList<Integer> lstOfKeys = new ArrayList<>();
        lstOfKeys.addAll(holdQuestions.keySet());
        Collections.sort(lstOfKeys);

        for (Integer i : lstOfKeys){
            addQuestion(Objects.requireNonNull(holdQuestions.get(i)));
        }

        /*
        for(int i = 1000; i < holdQuestions.size(); i++) {
            if(holdQuestions.get)
            addQuestion(Objects.requireNonNull(holdQuestions.get(i)));
        }

         */

        holdQuestions.clear();
        /*

       Questions question =  new Questions("What is the sun", "A","B","C","A",chapter1);

        //Questions question =  new Questions(this.res.getString(R.string.app_name), this.res.getString(R.string.app_name),this.res.getString(R.string.app_name),this.res.getString(R.string.app_name),this.res.getString(R.string.app_name));
        addQuestion(question);

        Questions question2 =  new Questions("What is the moon", "A","B","C","B",chapter1);
        addQuestion(question2);
        Questions question3 =  new Questions("What is the sky", "A","B","C","C",chapter1);
        addQuestion(question3);
        Questions question4 =  new Questions("What is the rain", "A","B","C","A",chapter1);
        addQuestion(question4);
        Questions question5 =  new Questions("What is the tie", "A","B","C","B",chapter1);
        addQuestion(question5);


         */




    }

    private void addQuestion(Questions questions) {
        ContentValues cv = new ContentValues();
        cv.put(QuizConsts.QuestionsTable.COLUMN_QUESTIONS, questions.getQuestion());
        cv.put(QuizConsts.QuestionsTable.COLUMN_ANSWER1, questions.getAnswer1());
        cv.put(QuizConsts.QuestionsTable.COLUMN_ANSWER2, questions.getAnswer2());
        cv.put(QuizConsts.QuestionsTable.COLUMN_ANSWER3, questions.getAnswer3());
        cv.put(QuizConsts.QuestionsTable.COLUMN_ANSWER_ID, questions.getRightAnswer());
        cv.put(QuizConsts.QuestionsTable.CATEGORY, questions.getCategory());
        db.insert(TABLE_NAME,null,cv);
    }


    @SuppressLint("Range")
    public List<Questions> getAllQuestions(String quiz_table, String category) {
        List<Questions> questionsList = new ArrayList<>();
        db = getReadableDatabase();
        TABLE_NAME = quiz_table;
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        if(c.moveToFirst()) {
            do {
                Questions questions = new Questions();
                questions.setQuestion(c.getString(c.getColumnIndex(QuizConsts.QuestionsTable.COLUMN_QUESTIONS)));
                questions.setAnswer1(c.getString(c.getColumnIndex(QuizConsts.QuestionsTable.COLUMN_ANSWER1)));
                questions.setAnswer2(c.getString(c.getColumnIndex(QuizConsts.QuestionsTable.COLUMN_ANSWER2)));
                questions.setAnswer3(c.getString(c.getColumnIndex(QuizConsts.QuestionsTable.COLUMN_ANSWER3)));
                questions.setRightAnswer(c.getString(c.getColumnIndex(QuizConsts.QuestionsTable.COLUMN_ANSWER_ID)));
                questions.setCategory(c.getString(c.getColumnIndex((QuizConsts.QuestionsTable.CATEGORY))));
                if(category.equals(questions.getCategory())) {
                    questionsList.add(questions);
                }
            } while(c.moveToNext());
        }
        c.close();
        return questionsList;
    }

    @SuppressLint("Range")
    public List<Questions> getAllQuizQuestions() {
        String category;
        String[] quiz_chapters = {hardChap1,hardChap2,chapter1,chapter2,hardChap3,hardChap4,chapter3,chapter4,chapter5,chapter6,hardChap6,hardChap7,chapter7,chapter8
                ,hardChap5,hardChap8};
        String[] quiz_table_list = {MUS_TABLE_NAME,MATH_TABLE_NAME,SCI_TABLE_NAME,HIS_TABLE_NAME,ARTS_TABLE_NAME,GEO_TABLE_NAME};

        List<Questions> questionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = null;
        for (String quiz_table : quiz_table_list) {
            for (String quiz_chapter : quiz_chapters) {
                TABLE_NAME = quiz_table;
                category = quiz_chapter;
                c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
                if (c.moveToFirst()) {
                    do {
                        Questions questions = new Questions();
                        questions.setQuestion(c.getString(c.getColumnIndex(QuizConsts.QuestionsTable.COLUMN_QUESTIONS)));
                        questions.setAnswer1(c.getString(c.getColumnIndex(QuizConsts.QuestionsTable.COLUMN_ANSWER1)));
                        questions.setAnswer2(c.getString(c.getColumnIndex(QuizConsts.QuestionsTable.COLUMN_ANSWER2)));
                        questions.setAnswer3(c.getString(c.getColumnIndex(QuizConsts.QuestionsTable.COLUMN_ANSWER3)));
                        questions.setRightAnswer(c.getString(c.getColumnIndex(QuizConsts.QuestionsTable.COLUMN_ANSWER_ID)));
                        questions.setCategory(c.getString(c.getColumnIndex((QuizConsts.QuestionsTable.CATEGORY))));
                        if (category.equals(questions.getCategory())) {
                            questionsList.add(questions);
                        }
                    } while (c.moveToNext());
                }
                c.close();
            }}

        return questionsList;
    }




}
