# PHP Assignment Tasks

## First 
- [x] switch to using JSONSTorage, load the cars to individual car objects, instead of displaying them from raw json

- [x] finish implementing filtering functionalities
    - [x] store the reservations in some way, to then query, when filtered for available cars at given date range
        - json, user, car id, start date, end date
    

- [x] write back all the details of the cars to the main page on the cards, instead of just displaying them on the dedicated details page

- [ ] filter for reservations in the reservationrepository somehow

## Admin funkciók
- [x] Külön adminisztrátori bejelentkezési lehetőség (alapértelmezett admin: e-mail: admin@ikarrental.hu, jelszó: admin).
- [ ] Az adminisztrátor "Profil" oldalán elérhető az összes foglalás
- Az adminisztrátor képes:
    - [ ] **Új autókat hozzáadni**, menti is ha megfeleloek az adatok
        - [ ] auto kartyak elso peldanya admin eseten egy + kepu add car lehetoseg, ez egy hasonlo oldalra visz mint a car details, csak meg kell adni minden adatot, kepet linkkent
        - autot hasonloan  adja hozza mint felhasznalot regisztralaskor
    - [ ] Meglévő autók adatait szerkeszteni.
    - [ ] Az "Autó szerkesztése" aloldalon lehessen módosítani az összes alapadatát (az azonosítón kívül), és az autóra vonatkozó meglévő foglalást törölni.
    - [ ] Autókat törölni.

- [ ] add header to every page