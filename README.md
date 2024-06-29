# Kleine Desktop-Toolbox-Anwendung

Dieses Projekt wurde im Rahmen des Kurses "Objektorientiertes Programmieren 1" entwickelt. Es bietet eine kompakte Sammlung von nützlichen Desktop-Anwendungen, die im täglichen Gebrauch hilfreich sind.

## Inhaltsverzeichnis

- [Überblick](#überblick)
- [Funktionen](#funktionen)
  - [PassSave](#passsave)
  - [NoteSave](#notesave)
  - [Calculator](#calculator)
  - [Alarm](#alarm)
- [Technische Details](#technische-details)
  - [Verwendete Technologien](#verwendete-technologien)
  - [Projektstruktur](#projektstruktur)
- [Installation und Ausführung](#installation-und-ausführung)
- [Zeitplan und Meilensteine](#zeitplan-und-meilensteine)
- [Beitragende](#beitragende)
- [Lizenz](#lizenz)

## Überblick

Die Kleine Desktop-Toolbox-Anwendung ist eine JavaFX-basierte Anwendung, die aus vier Hauptmodulen besteht:
1. PassSave
2. NoteSave
3. Calculator
4. Alarm

## Funktionen

### PassSave

- **Beschreibung:** Speichern und Verwalten von Benutzernamen und Passwörtern.
- **Merkmale:** Hinzufügen, Bearbeiten und Löschen von Passwörtern.

### NoteSave

- **Beschreibung:** Einfache Notizen machen und als Todo-Liste verwenden.
- **Merkmale:** Hinzufügen, Anzeigen und Löschen von Notizen.

### Calculator

- **Beschreibung:** Ein einfacher Taschenrechner für grundlegende mathematische Operationen.
- **Merkmale:** Addition, Subtraktion, Multiplikation und Division.

### Alarm

- **Beschreibung:** Stellen von Alarmen und Timern.
- **Merkmale:** Alarm setzen, Countdown-Timer, Wiederholungsfunktion.

## Technische Details

### Verwendete Technologien

- **Programmiersprache:** Java SE 21
- **Framework:** JavaFX
- **Build-Tool:** Maven
- **Version Control:** Git

### Projektstruktur

```plaintext
justtest1/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── test1/
│   │   │       ├── alarmController.java
│   │   │       ├── calcuController.java
│   │   │       ├── editPassController.java
│   │   │       ├── menuController.java
│   │   │       ├── noteController.java
│   │   │       ├── passController.java
│   │   │       ├── settingController.java
│   │   │       ├── UserPass.java
│   │   │       └── App.java
│   │   ├── resources/
│   │   │   ├── alarm.fxml
│   │   │   ├── calcu.fxml
│   │   │   ├── editPass.fxml
│   │   │   ├── menu.fxml
│   │   │   ├── note.fxml
│   │   │   ├── pass.fxml
│   │   │   ├── setting.fxml
│   │   │   ├── dark-theme.css
│   │   │   └── main.css
│   └── module-info.java


## Installation und Ausführung

1. **Repository klonen:**
   ```bash
   git clone https://github.com/blueyes0101/KleineDesktopToolbox



   **Zeitplan und Meilensteine**
Milestone a: Zeit-Planung (17.05.2024 - 18.05.2024)
Beschreibung: Erstellung eines allgemeinen Zeitplans für das Projekt.

Milestone b: Projekt-Setup, Test-Klasse, Git-Setup (19.05.2024 - 21.05.2024)
Beschreibung: Einrichtung der Projektstruktur und Erstellung eines GitHub-Repositories.

Milestone c: PassSave Modul (22.05.2024 - 25.05.2024)
Beschreibung: Entwicklung des Moduls zum Speichern von Benutzernamen und Passwörtern.

Milestone d: NoteSave Modul (26.05.2024 - 30.05.2024)
Beschreibung: Entwicklung des Moduls zum schnellen Notieren und Verwenden als Todo-Liste.

Milestone e: Calculator Modul (31.05.2024 - 03.06.2024)
Beschreibung: Entwicklung eines einfachen Taschenrechnermoduls.

Milestone f: Alarm Modul (04.06.2024 - 06.06.2024)
Beschreibung: Entwicklung eines Alarmmoduls, das zu bestimmten Zeiten warnt und einen Countdown hat.

Milestone g: Integration und Test (07.06.2024 - 09.06.2024)
Beschreibung: Integration aller Module und Durchführung eines allgemeinen Tests des Projekts.

Milestone h: Dokumentation und Abschluss (10.06.2024 - 11.06.2024)
Beschreibung: Dokumentation und Einreichung des Projekts.