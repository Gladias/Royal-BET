import React, { useState, useEffect } from 'react';
import nextId from 'react-id-generator';
import Form from 'react-bootstrap/Form';
import axios from 'axios';
import { useHistory } from 'react-router';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFutbol, faBasketballBall, faCar, faTrash } from '@fortawesome/free-solid-svg-icons';
import BASE_API_URL from '../constants/const';
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
  const { game, game: { hostTeam, visitorsTeam }, onClick } = props;
  const { odds: { hostWinOdds, tieOdds, visitorsWinOdds } } = game;

  // <div className={styles.score}>1-0</div>

  return (
    <div className={styles['game-row']}>
      <div className={styles.league}>
        <p>NBA</p>
      </div>
      <div className={styles.teams}>
        <h4>
          {hostTeam}
          {' - '}
          {visitorsTeam}
        </h4>
      </div>
      <div className={styles['result-title']}>
        <h4>Winner</h4>
      </div>
      <div className={styles.result}>
        <div>
          <p>{hostTeam}</p>
          <button onClick={() => onClick(game, hostTeam)} type="button">{hostWinOdds}</button>
        </div>
        <div>
          <p>Tie</p>
          <button onClick={() => onClick(game, 'Tie')} type="button">{tieOdds}</button>
        </div>
        <div>
          <p>{visitorsTeam}</p>
          <button onClick={() => onClick(game, visitorsTeam)} type="button">{visitorsWinOdds}</button>
        </div>
      </div>
    </div>
  );
}

function BetRow(props) {
  const { bet: { game: { hostTeam, visitorsTeam }, winner }, onChange, onClick } = props;
  const { bet } = props;

  return (
    <div className={styles.bet}>
      <div>
        <button type="button" onClick={() => onClick(bet)}>
          <FontAwesomeIcon icon={faTrash} />
        </button>
        <h5>
          {hostTeam}
          {' - '}
          {visitorsTeam}
        </h5>
      </div>
      <div>
        <h3>
          {'Winner '}
          {winner}
        </h3>
        <Form.Control onChange={onChange(bet)} type="number" size="sm" min="1" required />
      </div>
    </div>
  );
}

function MainPage() {
  const [possibleWinnings, setPossibleWinnings] = useState(0);
  const [bets, setBets] = useState([]);
  const [games, setGames] = useState([]);

  const history = useHistory();

  const mapGames = (rawGames) => {
    rawGames.forEach((game) => {
      setGames((prevState) => ([
        ...prevState,
        game,
      ]));
    });

    console.log(games);
  };

  useEffect(() => {
    axios.get(`${BASE_API_URL}/games`)
      .then((e) => {
        mapGames(e.data.content);
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);

  useEffect(() => {
    // Calculate accumulated possible winnings for bets
    let accumulatedWinnings = bets.reduce((prev, cur) => prev + cur.possibleWinnings, 0);
    if (Number.isNaN(accumulatedWinnings)) {
      accumulatedWinnings = 0;
    }

    setPossibleWinnings(Math.round(accumulatedWinnings * 100) / 100);
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
    console.log(games);
    const index = bets.findIndex((element) => element.id === bet.id);
    const value = parseFloat(e.target.value, 10);
    const modifiedBets = bets.slice();

    modifiedBets[index].stake = value;

    if (bet.winner === bet.game.host) {
      modifiedBets[index].possibleWinnings = bet.game.odds.hostWinOdds * value;
    } else if (bet.winner === bet.game.visitors) {
      modifiedBets[index].possibleWinnings = bet.game.odds.visitorsWinOdds * value;
    } else {
      modifiedBets[index].possibleWinnings = bet.game.odds.tieOdds * value;
    }

    setBets([...modifiedBets]);
  };

  const submitBets = (e) => {
    e.preventDefault();
    const betsDto = [];

    bets.forEach((bet) => {
      betsDto.push({
        stake: bet.stake,
        winner: bet.winner,
        gameId: bet.game.id,
      });
    });

    const request = {
      bets: betsDto,
    };

    axios.post(`${BASE_API_URL}/bets`, request)
      .then((response) => {
        console.log(response);
        history.push('/profile');
      })
      .catch((error) => {
        console.log(error);
      });
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
          {games.map((game) => <GameRow key={game.id} game={game} onClick={createBet} />)}
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
          <button type="button" onClick={submitBets}>Confirm bets</button>
        </div>
      </div>
    </div>
  );
}

export default MainPage;
