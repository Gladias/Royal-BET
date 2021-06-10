import React, { useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlusCircle } from '@fortawesome/free-solid-svg-icons';
import BASE_API_URL from '../constants/const';
import styles from './Profile.module.css';

function ProfileBet(props) {
  const { bet: { game: { league, host, visitors }, winner, stake } } = props;

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
      <div className={styles['result-title']}>
        <h4>
          Winner
          {' '}
          {winner}
        </h4>
      </div>
      <div className={styles.result}>
        <h3>
          Bet size:
          {' '}
          {stake}
          $
        </h3>
      </div>
    </div>
  );
}

function ProfilePage() {
  const user = {
    login: 'Gladias',
    balance: 14.54,
  };

  useEffect(async () => {
    axios.get(`${BASE_API_URL}/bets`)
      .then((e) => {
        console.log(e);
      })
      .catch((e) => {
        console.log(e);
      });
  });

  const game1 = {
    id: 1,
    league: 'Champions League',
    host: 'Juventus',
  };

  const bet = {
    id: 1,
    game: game1,
    stake: 50,
    winner: game1.host,
    possibleWinnings: 0,
  };

  return (
    <div className={styles.main}>
      <div className={styles.container}>
        <div className={styles.row}>
          <h2>
            Hi,
            {' '}
            {user.login}
            !
          </h2>
          <h2>
            Your balance is
            {' '}
            {user.balance}
            $
          </h2>
        </div>
        <div className={styles.row}>
          <ButtonGroup>
            <Button variant="success">Active Bets</Button>
            <Button variant="light">Expired Bets</Button>
          </ButtonGroup>

          <div>
            <button type="button">
              <FontAwesomeIcon icon={faPlusCircle} />
            </button>
            <h3>Increase balance</h3>
          </div>
        </div>
        <div className={styles.box}>
          <ProfileBet bet={bet} />
          <ProfileBet bet={bet} />
          <ProfileBet bet={bet} />
        </div>
      </div>
    </div>
  );
}

export default ProfilePage;
