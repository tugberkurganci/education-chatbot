const { Metaplex, keypairIdentity, bundlrStorage } = require('@metaplex/js');
const express = require('express');
const bodyParser = require('body-parser');
const { Connection, PublicKey, Keypair, SystemProgram, Transaction } = require('@solana/web3.js');
const bs58 = require('bs58'); // base58 kütüphanesini kullanıyoruz
const { create } = require('ipfs-http-client');

const app = express();
const port = 3001;

// Testnet bağlantısı
const connection = new Connection('https://api.devnet.solana.com', 'confirmed');
// Middleware
app.use(bodyParser.json());

// Anahtar çiftlerini tanımlayın (Base58 formatında)
const secretKeyBase58 = '';
const fromSecretKey = bs58.decode(secretKeyBase58);
const fromKeypair = Keypair.fromSecretKey(Uint8Array.from(fromSecretKey));

// Public key
const publicKey = new PublicKey('EUf8XcKwsvfu8bQUgpoDjr9E6gyGCwTpgvARG4z7vMwU');

app.get('/balance', async (req, res) => {
    const { publicKeyString } = req.query;  // Query'den gelen public key

    if (!publicKeyString) {
        return res.status(400).json({ error: 'Missing public key parameter' });
    }

    try {
        const publicKey = new PublicKey(publicKeyString);  // Gelen string'i PublicKey nesnesine dönüştür
        const balance = await connection.getBalance(publicKey);  // Belirtilen public key için bakiye kontrolü
        res.json({ balance });
    } catch (error) {
        console.error('Balance check failed:', error);
        res.status(500).json({ error: 'Failed to retrieve balance', details: error.message });
    }
});

app.post('/transfer', async (req, res) => {
    const { toPublicKeyString, amount } = req.body;

    if (!toPublicKeyString || !amount) {
        return res.status(400).json({ error: 'Missing parameters' });
    }

    try {
        const toPublicKey = new PublicKey(toPublicKeyString);;
        const lamports =amount; 

        const tx = new Transaction().add(
            SystemProgram.transfer({
                fromPubkey: fromKeypair.publicKey,
                toPubkey: toPublicKey,
                lamports,
            }),
        );

        const signature = await connection.sendTransaction(tx, [fromKeypair]);
        await connection.confirmTransaction(signature);

        res.json({ success: true, signature });
    } catch (error) {
        console.error('Transaction failed:', error);
        res.status(500).json({ error: 'Transaction failed' });
    }
});


app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
});