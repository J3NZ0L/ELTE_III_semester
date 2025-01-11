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
    - [x] **Új autókat hozzáadni**, menti is ha megfeleloek az adatok
        - [x] auto kartyak elso peldanya admin eseten egy + kepu add car lehetoseg, ez egy hasonlo oldalra visz mint a car details, csak meg kell adni minden adatot, kepet linkkent
        - autot hasonloan  adja hozza mint felhasznalot regisztralaskor
    - [ ] Meglévő autók adatait szerkeszteni.
    - [ ] Az "Autó szerkesztése" aloldalon lehessen módosítani az összes alapadatát (az azonosítón kívül), és az autóra vonatkozó meglévő foglalást törölni.
    - [ ] Autókat törölni.

- [x] add header to every page

- [ ] validate every form and input according to the data type of it correctly, if it is expected
- 1.0 pont Hitelesítés: A regisztráció hibakezeléssel működik 
    - 1.0 pont Hitelesítés: A bejelentkezés hibakezeléssel működik 
    
- **NO need to implement:**
    
    - [ ] 1.0 pont Hitelesítés: Sikeres bejelentkezés esetén az oldalakon látszódik, hogy be vagyunk jelentkezve 
    - [ ] 1.0 pont Kijelentkezés: Profiloldalon és minden oldalon elérhető 
    - [ ] 2.0 pont Autóoldal: A kiválasztott autót le tudom foglalni két időpont között, sikeres foglalás esetén a foglalás elmentődik 
    - [ ] 1.0 pont Autóoldal: Sikeres és sikertelen foglalás esetén a felhasználó értesítve van, sikeres esetén megjelennek a foglalás és az autó adatai 
    - [ ] 1.0 pont Főoldal: A főoldalon tudunk szűrni a szabad időpontokra is 
    - [ ] 1.0 pont Profiloldal: Megjelennek a felhasználó korábbi foglalásai 
    - [ ] 1.0 pont Admin: Az admin bejelentkezése esetén a profil oldalán megjelenik az összes foglalás, ezek a foglalások törölhetőek 
    - [ ] 1.0 pont Admin: Autók adatainak módosítása (hibakezeléssel) 
    - [ ] 1.0 pont Admin: Autók törlése 
    - [ ] 2.0 pont Megjelenés: Igényes, mobilbarát megjelenés 