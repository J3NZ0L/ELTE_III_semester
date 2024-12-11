# Todos regarding the assignment
- [x] add a visible Timer

- [x] distinct the light trails of the two players
- [x] reintegrate the movement of the players from gpt
- [x] optimize the painting or smth
  - [x] check if really just the turning points are stored, or the whole point array inneffectively
        - every point is stored, which is not efficient:
            - instead, should store just the turning points, and then check if the new lines intersect with any of the
              former lines (created by the last turning point and the current pos of players)

- [x] transition from using independent x and y coordinates to using Point objects as the players' current position
- [x] fix the trails, they are neither showing or being taken into account
- [x] check if the turning points are added and taken into account
  - they are not taken into account correctly, every point is added, not just turning points
- [x] somehow add the first two points to the array, instead of just the first, which is the case currently
- [x] test the new collision checking thoroguhly
  - [x] deter the lines being diagonalxd
  - [x] the last lines sometimes gets dragged, the end point of it

- [ ] see if the else if branch of the if statement in the move method is fully needed or not
- [ ] getters and setters for everything which is ought to be used outside of it's class
- [ ] decouple the app into two components: model and view (MV architecture) 
 
- [ ] create class diagram, not excluding the view part, with just the classes and their relationships

- [ ] figure out a lot of test cases, including ones which are quite logical and self-evident

- [ ] generate the javadocs for every method and simplify them 