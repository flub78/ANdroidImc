# AndroidImc

OpenClassroom "Créez des applications pour Android"

This app compute your body mass index (BMI) or IMC in French.

## Features

- Compute MBI
- Fixed Lint warnings
- Change BMI color depending on the range
- Manage local en, fr
- Compute BMI when the user hits "Enter"

## To do list

### DevOps
- Implement unit tests

### Refactoring

- Change toast attributes
- Include drawable images, icons in the toast messages, etc.

### Improvements

### Features
- Store history
- Display curves
- Manage several profiles

## Architecture and design

### Activities

- Login or user registration

- Data imput and BMI computation
    This activity should contain a button to store the data
    (user, date, weight, size)
    
    View history
        - display curve
        - display data table
         