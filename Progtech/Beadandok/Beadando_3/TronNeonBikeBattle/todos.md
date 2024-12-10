# Todos regarding the assignment
- [x] add a visible Timer

- [x] distinct the light trails of the two players
- [x] reintegrate the movement of the players from gpt
- [x] optimize the painting or smth
  - [x] check if really just the turning points are stored, or the whole point array inneffectively
        - every point is stored, which is not efficient:
            - instead, should store just the turning points, and then check if the new lines intersect with any of the
              former lines (created by the last turning point and the current pos of players)
- [ ]
- [ ] transition from using independent x and y coordinates to using Point objects as the players' current position
- [ ] getters and setters for everything which is ought to be used outside of it's class
- [ ] check if the turning points are added and taken into account
- [ ] decouple the app into two components: model and view (MV architecture) 
 
- [ ] create class diagram, not excluding the view part, with just the classes and their relationships

- [ ] figure out a lot of test cases, including ones which are quite logical and self-evident

- [ ] generate the javadocs for every method and simplify them 