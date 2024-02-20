import { QueryClient, QueryClientProvider } from 'react-query';

import '../app/globals.css';
import Navbar from './components/Navbar';

const queryClient = new QueryClient();

const App = ({ Component, pageProps }) => {
    return (
        <QueryClientProvider client={queryClient}>
            <Navbar />
            <Component {...pageProps} />
        </QueryClientProvider>
    );
};

export default App;