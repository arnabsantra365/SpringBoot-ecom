import React, { createContext,useContext,useState } from 'react'

interface AuthContexType{
  userId: number | null;
  login: (userId: number) => void;
  logout: () => void;
}
const AuthContext = createContext<AuthContexType|undefined>(undefined);

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
      throw new Error("useAuth must be used within an AuthProvider");
    }
    return context;
  };

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [userId, setUserId] = useState<number | null>(() => {
        const storedUserId = sessionStorage.getItem("userId");
        console.log(storedUserId);
        return storedUserId ? JSON.parse(storedUserId) : null;
        // return null
      });
    
      const login = (id: number) => {
        console.log(id);
        setUserId(id);
        sessionStorage.setItem("userId", JSON.stringify(id));
        // const storedUserId = sessionStorage.getItem("userId");
        // console.log(storedUserId); // Persist user session
      };
    
      const logout = () => {
        setUserId(null);
        sessionStorage.removeItem("userId");
        console.log(userId);
      };
    
      return (
        <AuthContext.Provider value={{ userId, login, logout }}>
          {children}
        </AuthContext.Provider>
      );
};

