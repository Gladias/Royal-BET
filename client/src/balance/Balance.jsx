import React, { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import axios from 'axios';
import { useHistory } from 'react-router';
import BASE_API_URL from '../constants/const';
import styles from './Balance.module.css';

function BalancePage() {
  const [user, setUser] = useState({});
  const [inputValue, setInputValue] = useState(1);
  const [withdrawEnable, setWithdrawEnable] = useState(true);
  const history = useHistory();

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
  }, []);

  const modifyValue = (e) => {
    const { value } = e.target;
    if (parseFloat(value) > user.money) {
      setWithdrawEnable(false);
    } else {
      setWithdrawEnable(true);
    }

    setInputValue(parseFloat(value));
  };

  const changeBalance = (variation) => {
    let request;

    if (variation === 'deposit') {
      request = {
        balance: inputValue,
      };
    } else {
      request = {
        balance: -inputValue,
      };
    }

    axios.post(`${BASE_API_URL}/balance`, request)
      .then(() => {
        history.push('/profile');
      })
      .catch((e) => {
        console.log(e);
      });
  };

  return (
    // eslint-disable-next-line react/self-closing-comp
    <div className={styles.main}>
      <div className={styles.container}>
        <div className={styles.row}>
          <h2>
            Your balance is
            {' '}
            {user.money}
            $
          </h2>
        </div>
        <div className={styles.row}>
          <h1>
            What you would like to do with your money?
          </h1>
        </div>
        <div className={styles.row}>
          <input
            type="number"
            value={inputValue}
            onChange={modifyValue}
            placeholder="$"
            min="1"
          />
        </div>
        <div className={styles.row}>
          <div className={styles.buttons}>
            <Button variant="success" onClick={() => changeBalance('deposit')}>Deposit</Button>
            {withdrawEnable
              ? <Button variant="danger" active onClick={() => changeBalance('withdraw')}>Withdraw</Button>
              : <Button variant="danger" disabled onClick={() => changeBalance('withdraw')}>Withdraw</Button>}
          </div>
        </div>
      </div>
    </div>
  );
}

export default BalancePage;
