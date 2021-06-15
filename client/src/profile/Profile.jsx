/* eslint-disable max-len */
/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import axios from 'axios';
import { useHistory } from 'react-router';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlusCircle } from '@fortawesome/free-solid-svg-icons';
import BASE_API_URL from '../constants/const';
import styles from './Profile.module.css';

function ProfileBet(props) {
  const { bet: { game: { time, hostTeam, visitorsTeam }, winner, stake } } = props;
  const { bet: { status: { status, win } } } = props;

  return (
    // eslint-disable-next-line no-nested-ternary
    <div className={`${styles['game-row']} ${(status === 'Expired') ? ((win === 0) ? styles.lostBet : styles.wonBet) : styles.default}`}>
      <div className={styles.league}>
        <p>{time.replace('T', ' ')}</p>
      </div>
      <div className={styles.teams}>
        <h4>
          {hostTeam}
          {' - '}
          {visitorsTeam}
        </h4>
      </div>
      {status !== 'Expired'
        ? (
          <>
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
          </>
        )
        : (
          <>
            <div className={styles['result-title']}>
              {win === 0
                ? (
                  <h4>
                    You have lost
                  </h4>
                )
                : (
                  <h4>
                    You have won!
                  </h4>
                )}
            </div>
            <div className={styles.result}>
              {win === 0
                ? (
                  <h3>
                    Try again
                  </h3>
                )
                : (
                  <h3>
                    Profit
                    {' '}
                    {win}
                    $
                  </h3>
                )}
            </div>
          </>
        )}
    </div>
  );
}

function ProfilePage() {
  const [user, setUser] = useState({});

  const [bets, setBets] = useState([]);
  const [activeBets, setActiveBets] = useState([]);
  const [expiredBets, setExpiredBets] = useState([]);

  const history = useHistory();

  const mapBets = (rawBets) => {
    rawBets.forEach((bet) => {
      setBets((prevState) => ([
        ...prevState,
        bet,
      ]));
      if (bet.status.status === 'Ongoing') {
        setActiveBets((prevState) => ([
          ...prevState,
          bet,
        ]));
      } else {
        setExpiredBets((prevState) => ([
          ...prevState,
          bet,
        ]));
      }
    });
  };

  useEffect(async () => {
    axios.get(`${BASE_API_URL}/auth/userData`)
      .then((e) => {
        setUser({
          login: e.data.login,
          email: e.data.email,
          money: e.data.money,
        });
      })
      .catch((e) => {
        console.log(e);
      });

    axios.get(`${BASE_API_URL}/bets`)
      .then((e) => {
        mapBets(e.data.content);
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);

  const changeFilter = (filter) => {
    if (filter === 'active') {
      setBets(activeBets);
    } else {
      setBets(expiredBets);
    }
  };

  const goToBalance = () => {
    history.push('/balance');
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
            {user.money}
            $
          </h2>
        </div>
        <div className={styles.row}>
          <ButtonGroup>
            <Button variant="success" onClick={() => changeFilter('active')}>Active Bets</Button>
            <Button variant="light" onClick={() => changeFilter('expired')}>Expired Bets</Button>
          </ButtonGroup>

          <div>
            <button type="button" onClick={goToBalance}>
              <FontAwesomeIcon icon={faPlusCircle} />
            </button>
            <h3>Change balance</h3>
          </div>
        </div>
        <div className={styles.box}>
          {bets.map((bet) => <ProfileBet key={bet.id} bet={bet} />)}
        </div>
      </div>
    </div>
  );
}

export default ProfilePage;
