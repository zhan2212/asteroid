In this file, I will elaborate on the modifications that I made based on the original game.

## Minimum requirements:

1. Implement debris.
    I created a class of DebrisWeapon, whose bullets will generate debris when colliding with
    asteroids. The debris is defined in Debris class, they are in random shapes and random
    accelerations.
    Players can press C to use this weapon in the game.

2. Implement scoring.
    The current score, level and number of falcons will be shown at the top left corner of the
    game board.

3. When the falcon collides with a NewShipFloater, you should increment the number of falcons.
    As the falcon collides with a NewShipFloater, the number of falcons (shown in the top left corner)
    will increase by 1.

## Other modifications:

1. newWeapon.
    I implemented a new weapon in this game (newWeapon class). The bullet of this weapon will further generate more
    small bullets (SmallBullet class). These small bullets are ejected to different directions and thus can destroy
    asteroids.
    Players can press G to use this weapon in the game.

2. shield.
    The falcon can use shield to prevent itself from colliding with asteroids. In detail, the shield will exist for
    a short time period. During this time, any asteroids collide with the shield will be destroyed. More can be found
    in Shield class.
    Players can press A to use this weapon in the game.

3. clear all weapon.
    Once the falcon use this clear all weapon, it will destroy all the asteroids appearing in the current screen.
    The weapon is in a diamond shape and is gradually growing. It will destory the asteroids once it touches them.
    Players can press B to use this weapon in the game.