import express from 'express';
import { fileURLToPath } from 'node:url';
import { dirname, join, resolve } from 'node:path';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const DIST_FOLDER = join(__dirname, '../');
const browserDistFolder = join(DIST_FOLDER, 'browser');

const app = express();

/**
 * Serve static files from /browser
 */
app.use(express.static(browserDistFolder, {
  maxAge: '1y',
  index: 'index.html',
}));

/**
 * Handle all other requests by serving the Angular app
 */
app.get('*', (req, res) => {
  res.sendFile(join(browserDistFolder, 'index.html'));
});

/**
 * Start the server
 */
const port = process.env['PORT'] || 4000;
app.listen(port, () => {
  console.log(`Node Express server listening on http://localhost:${port}`);
});

export default app;
