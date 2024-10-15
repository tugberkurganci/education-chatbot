import { WalletAdapterNetwork } from '@solana/wallet-adapter-base';
import { ConnectionProvider, WalletProvider, useWallet } from '@solana/wallet-adapter-react';
import { WalletModalProvider, WalletMultiButton } from '@solana/wallet-adapter-react-ui';
import { UnsafeBurnerWalletAdapter } from '@solana/wallet-adapter-wallets';
import React, { FC, ReactNode, useEffect, useMemo, useState } from 'react';
import { clusterApiUrl, Connection, LAMPORTS_PER_SOL } from '@solana/web3.js';
import Home from './screens/Home';

require('./App.css');
require('@solana/wallet-adapter-react-ui/styles.css');

const App: FC = () => {
    const [walletState, setWalletState] = useState<string | null>(null);

    return (
        <Context>
            <div className="app-container">
                <Content setWalletState={setWalletState} />
                
                <Home walletState={walletState}/>
     </div>
        </Context>
    );
};

const Context: FC<{ children: ReactNode }> = ({ children }) => {
    const network = WalletAdapterNetwork.Devnet;
    const endpoint = useMemo(() => clusterApiUrl(network), [network]);

    const wallets = useMemo(
        () => [
            new UnsafeBurnerWalletAdapter(),
        ],
        [network]
    );

    return (
        <ConnectionProvider endpoint={endpoint}>
            <WalletProvider wallets={wallets} autoConnect>
                <WalletModalProvider>{children}</WalletModalProvider>
            </WalletProvider>
        </ConnectionProvider>
    );
};

const Content: FC<{ setWalletState: (walletAddress: string | null) => void }> = ({ setWalletState }) => {
    const { publicKey } = useWallet();
    const [balance, setBalance] = useState<number | null>(null);

    useEffect(() => {
        if (publicKey) {
            const connection = new Connection(clusterApiUrl(WalletAdapterNetwork.Devnet));
            connection.getBalance(publicKey).then(balance => {
                setBalance(balance / LAMPORTS_PER_SOL);
            });
            setWalletState(publicKey.toBase58());
        } else {
            setWalletState(null);
        }
    }, [publicKey, setWalletState]);

    return (
        <div className="App">
            <WalletMultiButton />
            {publicKey && <p>Your wallet address: {publicKey.toBase58()}</p>}
            {balance !== null && <p>Your balance: {balance} SOL</p>}
        </div>
    );
};

export { App };
