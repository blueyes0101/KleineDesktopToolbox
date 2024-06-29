# Kleine Desktop-Toolbox-Anwendung

Diese Projekt wurde im Rahmen des Kurses "Objektorientiertes Programmieren 1" entwickelt. Es bietet eine kompakte Sammlung von nützlichen Desktop-Anwendungen, die im täglichen Gebrauch hilfreich sind.

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