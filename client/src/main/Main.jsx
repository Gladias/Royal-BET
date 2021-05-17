import React, { useState, useEffect } from 'react';
import nextId from 'react-id-generator';
import Form from 'react-bootstrap/Form';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFutbol, faBasketballBall, faCar, faTrash } from '@fortawesome/free-solid-svg-icons';
import styles from './Main.module.css';

function CategoryButton(props) {
  const { name, icon } = props;
  return (
    <div className={styles['category-button']}>
      <FontAwesomeIcon icon={icon} />
      <h6>{name}</h6>
      <input type="radio" />
    </div>
  );
}

function GameRow(props) {
  const { game, game: { league, host, visitors, odds }, onClick } = props;
  const [hostWin, tie, visitorsWin] = Object.values(odds);

  return (
    <div className={styles['game-row']}>
      <div className={styles.league}>
        <p>{league}</p>
      </div>
      <div className={styles.teams}>
        <h4>
          {host}
          {' - '}
          {visitors}
        </h4>
      </div>
      <div className={styles.score}>1-0</div>
      <div className={styles['result-title']}>
        <h4>Winner</h4>
      </div>
      <div className={styles.result}>
        <div>
          <p>{host}</p>
          <button onClick={() => onClick(game, host)} type="button">{hostWin}</button>
        </div>
        <div>
          <p>Tie</p>
          <button onClick={() => onClick(game, 'Tie')} type="button">{tie}</button>
        </div>
        <div>
          <p>{visitors}</p>
          <button onClick={() => onClick(game, visitors)} type="button">{visitorsWin}</button>
        </div>
      </div>
    </div>
  );
}

function BetRow(props) {
  const { bet: { game: { host, visitors }, winner }, onChange, onClick } = props;
  const { bet } = props;

  return (
    <div className={styles.bet}>
      <div>
        <button type="button" onClick={() => onClick(bet)}>
          <FontAwesomeIcon icon={faTrash} />
        </button>
        <h3>
          {host}
          {' - '}
          {visitors}
        </h3>
      </div>
      <div>
        <h3>
          {'Winner '}
          {winner}
        </h3>
        <Form.Control onChange={onChange(bet)} type="number" size="sm" required />
      </div>
    </div>
  );
}

function MainPage() {
  const [possibleWinnings, setPossibleWinnings] = useState(0);
  const [bets, setBets] = useState([]);

  const game1 = {
    id: 1,
    league: 'Champions League',
    host: 'Juventus',
    visitors: 'Porto',
    odds: {
      host: 1.47,
      tie: 2.05,
      visitors: 2.56,
    },
  };

  useEffect(() => {
    // Calculate accumulated possible winnings for bets
    const accumulatedWinnings = bets.reduce((prev, cur) => prev + cur.possibleWinnings, 0);
    setPossibleWinnings(accumulatedWinnings);
  }, [JSON.stringify(bets)]);

  const createBet = (game, winner) => {
    const newBet = {
      id: nextId(),
      game,
      stake: 0,
      winner,
      possibleWinnings: 0,
    };

    setBets((prevState) => ([
      ...prevState,
      newBet,
    ]));
  };

  const deleteBet = (bet) => {
    const filteredBets = bets.filter((e) => e.id !== bet.id);
    setBets(() => filteredBets);
  };

  const modifyBet = (bet) => (e) => {
    const index = bets.findIndex((element) => element.id === bet.id);
    const value = parseFloat(e.target.value, 10);
    const modifiedBets = bets.slice();

    modifiedBets[index].stake = value;

    if (bet.winner === bet.game.host) {
      modifiedBets[index].possibleWinnings = bet.game.odds.host * value;
    } else if (bet.winner === bet.game.visitors) {
      modifiedBets[index].possibleWinnings = bet.game.odds.visitors * value;
    } else {
      modifiedBets[index].possibleWinnings = bet.game.odds.tie * value;
    }

    setBets([...modifiedBets]);
  };

  return (
    <div className={styles.main}>
      <div className={styles['first-column']}>

        <div className={styles.categories}>
          <CategoryButton name="Football" icon={faFutbol} />
          <CategoryButton name="Basketball" icon={faBasketballBall} />
          <CategoryButton name="Racing" icon={faCar} />
        </div>

        <div className={styles.games}>
          <GameRow game={game1} onClick={createBet} />
          <GameRow game={game1} onClick={createBet} />
          <GameRow game={game1} onClick={createBet} />
        </div>
      </div>
      <div className={styles['second-column']}>

        <div>
          <h1>YOUR BETS</h1>
        </div>

        <div className={styles.bets}>
          {bets.length > 0 && bets.map((d) => <BetRow bet={d} key={d.id} onClick={deleteBet} onChange={modifyBet} />)}
        </div>

        <div className={styles.footer}>
          <h2>
            Potential win: $
            {possibleWinnings}
          </h2>
          <button type="button">Confirm bets</button>
        </div>
      </div>
    </div>
  );
}

export default MainPage;
