import {EmptyTile, MountainTile, BridgeTile, Oasis} from './classes.js';

export const baseEasyLevels = [
    [
      [new EmptyTile(), new MountainTile(1), new EmptyTile(), new EmptyTile(), new Oasis()],
      [new EmptyTile(), new EmptyTile(), new EmptyTile(), new BridgeTile(0), new Oasis()],
      [new BridgeTile(), new EmptyTile(), new MountainTile(2), new EmptyTile(), new EmptyTile()],
      [new EmptyTile(), new EmptyTile(), new EmptyTile(), new Oasis(), new EmptyTile()],
      [new EmptyTile(), new EmptyTile(), new MountainTile(3), new EmptyTile(), new EmptyTile()],
    ],
    [
      [new Oasis(), new EmptyTile(), new BridgeTile(3), new EmptyTile(), new EmptyTile()],
      [new EmptyTile(), new MountainTile(2), new EmptyTile(), new EmptyTile(), new MountainTile(2)],
      [new BridgeTile(0), new Oasis(), new MountainTile(3), new EmptyTile(), new EmptyTile()],
      [new EmptyTile(),new EmptyTile(),new EmptyTile(), new Oasis(),new EmptyTile()],
      [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile()],
    ],
    [
      [new EmptyTile(),new EmptyTile(),new BridgeTile(3), new EmptyTile(),new EmptyTile()],
      [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new BridgeTile(2)],
      [new EmptyTile(), new MountainTile(2), new BridgeTile(0), new EmptyTile(),new EmptyTile()],
      [new EmptyTile(), new Oasis(), new EmptyTile(),new EmptyTile(),new EmptyTile()],
      [new EmptyTile(), new BridgeTile(3), new EmptyTile(),new EmptyTile(), new MountainTile(2)],
    ],
    [
      [new EmptyTile(),new EmptyTile(),new EmptyTile(), new BridgeTile(3), new EmptyTile(),],
      [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile()],
      [new BridgeTile(0), new EmptyTile(), new MountainTile(1), new EmptyTile(), new MountainTile(1)],
      [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile()],
      [new EmptyTile(),new EmptyTile(), new Oasis(), new MountainTile(3), new EmptyTile(),]
    ],
    [
      [new EmptyTile(),new EmptyTile(), new BridgeTile(3), new EmptyTile(),new EmptyTile()],
      [new EmptyTile(), new MountainTile(0), new EmptyTile(),new EmptyTile(),new EmptyTile()],
      [new BridgeTile(0), new EmptyTile(),new EmptyTile(), new MountainTile(3), new EmptyTile(),],
      [new EmptyTile(),new EmptyTile(), new BridgeTile(0), new Oasis(), new EmptyTile()],
      [new EmptyTile(), new MountainTile(2), new EmptyTile(), new EmptyTile(),new EmptyTile()],
      ]
]

export const baseHardLevels = [
  [
    [new EmptyTile(), new MountainTile(1), new Oasis(), new Oasis(), new EmptyTile(), new BridgeTile(3), new EmptyTile(),],
    [new BridgeTile(0), new EmptyTile(), new EmptyTile(), new EmptyTile(),new EmptyTile(),new EmptyTile(), new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(), new BridgeTile(), new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(),new EmptyTile(), new MountainTile(3), new EmptyTile(),new EmptyTile(),new EmptyTile(),],
    [new MountainTile(3), new EmptyTile(), new MountainTile(1), new EmptyTile(), new BridgeTile(3), new EmptyTile(), new Oasis()],
    [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(),new EmptyTile(), new BridgeTile(3), new EmptyTile(),new EmptyTile(),new EmptyTile(),]
  ],
  [
    [new EmptyTile(),new EmptyTile(), new Oasis(), new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),], 
    [new BridgeTile(), new EmptyTile(), new BridgeTile(3), new EmptyTile(),new EmptyTile(),new MountainTile(2), new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(), new BridgeTile(3), new EmptyTile(),new EmptyTile(),new EmptyTile(), new BridgeTile()],
    [new MountainTile(), new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),],
    [new EmptyTile(), new Oasis(), new EmptyTile(), new MountainTile(1), new EmptyTile(),new EmptyTile(),new EmptyTile(),],
    [new EmptyTile(), new MountainTile(), new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(), new Oasis(), new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),]
  ],
  [
    [new EmptyTile(),new EmptyTile(),new BridgeTile(3), new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(), new BridgeTile()],
    [new Oasis(), new EmptyTile(), new MountainTile(3), new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),],
    [new EmptyTile(), new Oasis(), new MountainTile(3), new EmptyTile(), new BridgeTile(3), new EmptyTile(),new EmptyTile(),],
    [new BridgeTile(), new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(), new MountainTile(1), new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(), new Oasis(), new MountainTile(3), new EmptyTile(),new EmptyTile(),new EmptyTile(),]
  ],
  [ 
    [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(),new EmptyTile(), new BridgeTile(), new EmptyTile(), new MountainTile(2), new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(), new MountainTile(3), new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),],
    [new EmptyTile(), new BridgeTile(3), new EmptyTile(), new Oasis(), new EmptyTile(), new BridgeTile(3), new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(), new MountainTile(2), new EmptyTile(), new MountainTile(1), new EmptyTile(),new EmptyTile(),],
    [new BridgeTile(), new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(), new MountainTile(3) ,new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),],
  ],
  [
    [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(), new MountainTile(), new EmptyTile(),],
    [new EmptyTile(), new BridgeTile(3), new BridgeTile(3), new EmptyTile(), new MountainTile(1), new EmptyTile(),new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(), new MountainTile(), new EmptyTile(), new Oasis(), new EmptyTile(),new EmptyTile(),],
    [new EmptyTile(), new MountainTile(2), new EmptyTile(), new BridgeTile(), new EmptyTile(),new EmptyTile(),new EmptyTile(),],
    [new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),new EmptyTile(),]
  ]
]